package uy.gub.agesic.pge.core.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.picketlink.identity.federation.core.util.StringUtil;
import uy.gub.agesic.pge.AgesicConstants;
import uy.gub.agesic.pge.core.config.PGEConfig.KeyStore.Auth;
import uy.gub.agesic.pge.core.config.PGEConfig.STSConfig.Property;
import uy.gub.agesic.pge.exceptions.ConfigurationException;

/**
 *
 * @author fsierra
 *
 */
public final class PGEConfiguration {

	private static final Pattern EXPANSION_PATTERN = Pattern.compile("(\\$\\{([^}]+?)\\})", java.util.regex.Pattern.MULTILINE);

	private PGEConfig config;
	private Map<String, String> stsValues = new HashMap<String, String>();
	private Map<String, String> authValues = new HashMap<String, String>();

	public PGEConfiguration(String configFile) throws ConfigurationException {
		InputStream inputStream = null;
		try {

			configFile = resolveEnvVars(configFile);

			JAXBContext context = JAXBContext.newInstance(PGEConfig.class);
			inputStream = this.getConfigStream(configFile);
			config = (PGEConfig) context.createUnmarshaller().unmarshal(inputStream);

			List<Auth> authTypes = config.getKeyStore().getAuth();
			for (Auth authType : authTypes) {
				authValues.put(authType.getKey(), authType.getValue());
			}

			List<Property> propertyTypes = config.getSTSConfig().getProperty();
			for (Property propertyType : propertyTypes) {
				stsValues.put(propertyType.getKey(), propertyType.getValue());
			}

		} catch (JAXBException e) {
			throw new ConfigurationException(e.getMessage(), e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					Logger.getLogger(PGEConfiguration.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	//Agregado 10/03/2015 ver si no hay que borrar
	public PGEConfiguration(InputStream inputStream) throws ConfigurationException {
		try {

			JAXBContext context = JAXBContext.newInstance(PGEConfig.class);
			config = (PGEConfig) context.createUnmarshaller().unmarshal(inputStream);
			List<Auth> authTypes = config.getKeyStore().getAuth();
			for (Auth authType : authTypes) {
				authValues.put(authType.getKey(), authType.getValue());
			}

			List<Property> propertyTypes = config.getSTSConfig().getProperty();
			for (Property propertyType : propertyTypes) {
				stsValues.put(propertyType.getKey(), propertyType.getValue());
			}

		} catch (JAXBException e) {
			throw new ConfigurationException(e.getMessage(), e);
		}
	}

	public String getSTSURL() {
		return config.getSTSConfig().getUrl();
	}

	public String getKeyStoreAuthValue(String key) throws ConfigurationException {
		String value = authValues.get(key);
		if (value.startsWith(AgesicConstants.PASS_MASK_PREFIX)) {
			String salt = authValues.get(ConfigProperties.SALT);
			int iterationCount = Integer.parseInt(authValues.get(ConfigProperties.ITERATION_COUNT));
			try {
				return StringUtil.decode(value, salt, iterationCount);
			} catch (Exception e) {
				throw new ConfigurationException(e);
			}
		}

		return resolveEnvVars(value);

	}

	public String getSTSPropValue(String key) {
		return stsValues.get(key);
	}

	public Long getSTSLongPropValue(String key) {
		if (stsValues.get(key) != null) {
			return Long.parseLong(stsValues.get(key));
		}

		return null;
	}

	private String resolveEnvVars(String input) {
		if (input == null) {
			return null;
		}

		Map<?, ?> props = System.getProperties();

		Matcher matcher = EXPANSION_PATTERN.matcher(input);

		StringBuffer expanded = new StringBuffer(input.length());
		while (matcher.find()) {
			String propName = matcher.group(2);
			String value = (String) props.get(propName);
			if (value == null) {
				value = matcher.group(0);
			}
			matcher.appendReplacement(expanded, "");
			expanded.append(value);
		}
		matcher.appendTail(expanded);

		return expanded.toString();
	}

	private InputStream getConfigStream(String configPath) {
		InputStream is = null;
		try {
			File file = new File(configPath);
			return new FileInputStream(file);
		} catch (Exception e) {
			URL url = null;
			try {
				url = new URL(configPath);
				return url.openStream();
			} catch (Exception ex) {
				url = SecurityActions.loadResource(PGEConfiguration.class, configPath);
				if (url != null) {
					try {
						return url.openStream();
					} catch (IOException e1) {
					}
				}
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e1) {
					}
				}
			}
		}

		throw new RuntimeException("Config file not located: " + configPath);

	}

	//Agregado 10/03/2015 borrar
	public PGEConfig getConfig() {
		return config;
	}

}
