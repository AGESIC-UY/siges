package com.sofis.business.utils;

import com.sofis.generico.utils.generalutils.DatesUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class Pruebas {

	public static void main(String[] args) {
		long l = 262952150400000L;
		Date d = new Date(l);
		System.out.println("date:" + d.toString());

		Calendar cal = new GregorianCalendar();
		cal.set(2016, 11, 12, 12, 0, 0);
		System.out.println("cal:" + cal.toString());
		System.out.println("cal long:" + cal.getTimeInMillis());

		System.out.println("Date largo:" + DatesUtils.toFormatoLargo(new Date()));

		System.out.println("test:" + (String.format("resultado = %d", 40)));

		ConsultaFiltro3TO cf3 = new ConsultaFiltro3TO("aaa", "bbb", null, new Date(), new Date(), Integer.SIZE, 0, true);
		cf3.setFilasSeleccionadas(new ArrayList<Object[][]>());
		Object[][] e = new Object[][]{{"a", 10l}, {"b", 15l}};
		cf3.getFilasSeleccionadas().add(e);
		e = new Object[][]{{"a", 11l}, {"b", 12l}};
		cf3.getFilasSeleccionadas().add(e);
		Object obj = cf3;
//        Object obj = fi;

		OutputStream os = null;
		try {
			System.out.println("@@ objectToXml");
			System.out.println("@@ class:" + obj.getClass());
			JAXBContext jc = JAXBContext.newInstance(obj.getClass());
//            JAXBContext jc = JAXBContext.newInstance(ConsultaFiltro3TO.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			os = new ByteArrayOutputStream();
			marshaller.marshal(obj, os);
			System.out.println("return:" + os.toString());
		} catch (JAXBException ex) {
			ex.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException ex) {
					Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}

		Calendar cal2 = new GregorianCalendar();
		cal2.set(Calendar.DAY_OF_MONTH, 24);
		cal2.set(Calendar.MONTH, 2);
		cal2.set(Calendar.HOUR_OF_DAY, 12);
		cal2.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.SECOND, 0);
		cal2.set(Calendar.MILLISECOND, 0);
		System.out.println("cal2:" + cal2.getTimeInMillis());

	}

	/**
	 * Filtro para la consulta de Volumenes entre fechas.
	 *
	 * @author administrador
	 */
	@XmlRootElement
	public static class ConsultaFiltro3TO implements Serializable {

		private String portalUserId;
		private String portalUserRoles;
		private List<Object[][]> filasSeleccionadas;
		private Date fechaDesde;
		private Date fechaHasta;
		private Integer precioSeleccionado;
		private Integer agrupacion;
		private Boolean agruparFilas;

		public ConsultaFiltro3TO() {
		}

		public ConsultaFiltro3TO(String portalUserId, String portalUserRoles, List<Object[][]> filasSeleccionadas, Date fechaDesde, Date fechaHasta, Integer precioSeleccionado, Integer agrupacion, Boolean agruparFilas) {
			this.portalUserId = portalUserId;
			this.portalUserRoles = portalUserRoles;
			this.filasSeleccionadas = filasSeleccionadas;
			this.fechaDesde = fechaDesde;
			this.fechaHasta = fechaHasta;
			this.precioSeleccionado = precioSeleccionado;
			this.agrupacion = agrupacion;
			this.agruparFilas = agruparFilas;
		}

		public Integer getAgrupacion() {
			return agrupacion;
		}

		public void setAgrupacion(Integer agrupacion) {
			this.agrupacion = agrupacion;
		}

		public Boolean getAgruparFilas() {
			return agruparFilas;
		}

		public void setAgruparFilas(Boolean agruparFilas) {
			this.agruparFilas = agruparFilas;
		}

		public Date getFechaDesde() {
			return fechaDesde;
		}

		public void setFechaDesde(Date fechaDesde) {
			this.fechaDesde = fechaDesde;
		}

		public Date getFechaHasta() {
			return fechaHasta;
		}

		public void setFechaHasta(Date fechaHasta) {
			this.fechaHasta = fechaHasta;
		}

		public List<Object[][]> getFilasSeleccionadas() {
			return filasSeleccionadas;
		}

		public void setFilasSeleccionadas(List<Object[][]> filasSeleccionadas) {
			this.filasSeleccionadas = filasSeleccionadas;
		}

		public String getPortalUserId() {
			return portalUserId;
		}

		public void setPortalUserId(String portalUserId) {
			this.portalUserId = portalUserId;
		}

		public String getPortalUserRoles() {
			return portalUserRoles;
		}

		public void setPortalUserRoles(String portalUserRoles) {
			this.portalUserRoles = portalUserRoles;
		}

		public Integer getPrecioSeleccionado() {
			return precioSeleccionado;
		}

		public void setPrecioSeleccionado(Integer precioSeleccionado) {
			this.precioSeleccionado = precioSeleccionado;
		}
	}

}
