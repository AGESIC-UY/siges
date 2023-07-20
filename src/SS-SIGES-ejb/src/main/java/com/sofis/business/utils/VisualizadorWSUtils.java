package com.sofis.business.utils;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Image;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Organismos;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.agesic.siges.visualizador.web.ws.Organizaciones;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class VisualizadorWSUtils {

    private static final Logger logger = Logger.getLogger(VisualizadorWSUtils.class.getName());

    public static XMLGregorianCalendar dateToXMLGregorianCalendar(Long l) {
        if (l != null) {
            return dateToXMLGregorianCalendar(new Date(l));
        }
        return null;
    }

    public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date) {
        if (date != null) {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(date);
            try {
                XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
                return date2;
            } catch (DatatypeConfigurationException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public static org.agesic.siges.visualizador.web.ws.Moneda convertMoneda(Moneda monSiges) {
        if (monSiges != null) {
            org.agesic.siges.visualizador.web.ws.Moneda monVisua = new org.agesic.siges.visualizador.web.ws.Moneda();
            monVisua.setMonPk(monSiges.getMonPk());
            monVisua.setMonCodPais(monSiges.getMonCodPais());
            monVisua.setMonNombre(monSiges.getMonNombre());
            monVisua.setMonSigno(monSiges.getMonSigno());

            return monVisua;
        }
        return null;
    }

    public static org.agesic.siges.visualizador.web.ws.Image convertToImageWS(Image image) {
        if (image != null) {
            org.agesic.siges.visualizador.web.ws.Image imageImp = new org.agesic.siges.visualizador.web.ws.Image();
            imageImp.setImageName(image.getImageName());
            imageImp.setTipo(image.getTipo());
            imageImp.setImageDesc(image.getImageDesc());
            imageImp.setImagen(image.getImagen());
            imageImp.setImageExt(image.getImageExt());
            imageImp.setOrden(image.getOrden());
        }
        return null;
    }

    public static Image convertToImage(org.agesic.siges.visualizador.web.ws.Image imageWS) {
        if (imageWS != null) {
            Image image = new Image();
            image.setImageName(imageWS.getImageName());
            image.setTipo(imageWS.getTipo());
            image.setImageDesc(imageWS.getImageDesc());
            image.setImagen(imageWS.getImagen());
            image.setImageExt(imageWS.getImageExt());
            image.setOrden(imageWS.getOrden());
        }
        return null;
    }

    public static org.agesic.siges.visualizador.web.ws.CategoriaProyectos convertCategoriaProyectos(CategoriaProyectos cp) {
        if (cp != null) {
            org.agesic.siges.visualizador.web.ws.CategoriaProyectos cpVisua = new org.agesic.siges.visualizador.web.ws.CategoriaProyectos();
            cpVisua.setCatNombre(cp.getCatProyNombre());
            cpVisua.setCatCod(cp.getCatProyCodigo());
            cpVisua.setCatTipo(cp.getCatTipo());
            cpVisua.setVigente(cp.getCatProyActivo());
            cpVisua.setCatIcono(convertToImageWS(cp.getCatIcono()));
            cpVisua.setCatIconoMarker(convertToImageWS(cp.getCatIconoMarker()));
            return cpVisua;
        }
        return null;
    }

    public static CategoriaProyectos convertCategoriaProyectos(org.agesic.siges.visualizador.web.ws.CategoriaProyectos cpVisua, Integer orgPk) {
        if (cpVisua != null) {
            CategoriaProyectos cp = new CategoriaProyectos();
            cp.setCatProyNombre(cpVisua.getCatNombre());
            cp.setCatProyCodigo(cpVisua.getCatCod());
            cp.setCatTipo(cpVisua.getCatTipo());
            cp.setCatProyActivo(cpVisua.isVigente());
            cp.setCatIcono(convertToImage(cpVisua.getCatIcono()));
            cp.setCatIconoMarker(convertToImage(cpVisua.getCatIconoMarker()));
            if (orgPk != null) {
                cp.setCatOrgFk(new Organismos(orgPk));
            }
            return cp;
        }
        return null;
    }

    public static List<org.agesic.siges.visualizador.web.ws.CategoriaProyectos> convertCategoriaProyectos(List<CategoriaProyectos> cpList) {
        if (cpList != null) {
            List<org.agesic.siges.visualizador.web.ws.CategoriaProyectos> cpImpList = new ArrayList<>();
            for (CategoriaProyectos cp : cpList) {
                org.agesic.siges.visualizador.web.ws.CategoriaProyectos cpVisua = convertCategoriaProyectos(cp);
                if (cpVisua != null) {
                    cpImpList.add(cpVisua);
                }
            }
            return cpImpList;
        }
        return null;
    }

    public static List<org.agesic.siges.visualizador.web.ws.EntregablesImp> convertEntregables(List<Entregables> entList) {
        if (entList != null) {
            List<org.agesic.siges.visualizador.web.ws.EntregablesImp> entImpList = new ArrayList<>();
            for (Entregables ent : entList) {
                org.agesic.siges.visualizador.web.ws.EntregablesImp entVisua = new org.agesic.siges.visualizador.web.ws.EntregablesImp();
                entVisua.setEntImpDescripcion(ent.getEntDescripcion());
                entVisua.setEntImpFinDate(dateToXMLGregorianCalendar(ent.getEntFinDate()));
                entVisua.setEntImpFinLineaBaseDateDate(dateToXMLGregorianCalendar(ent.getEntFinLineaBaseDate()));
                entVisua.setEntImpId(ent.getEntId());
                entVisua.setEntImpInicioDate(dateToXMLGregorianCalendar(ent.getEntInicioDate()));
                entVisua.setEntImpInicioLineaBaseDate(dateToXMLGregorianCalendar(ent.getEntInicioLineaBaseDate()));
                entVisua.setEntImpNivel(ent.getEntNivel());
                entVisua.setEntImpNombre(ent.getEntNombre());
                entVisua.setEntImpProgreso(ent.getEntProgreso());
                entVisua.setEntImpEsfuerzo(ent.getEntEsfuerzo());

                entImpList.add(entVisua);
            }
            return entImpList;
        }
        return null;
    }

    public static org.agesic.siges.visualizador.web.ws.FuenteFinanciamiento convertFuenteFinanciamiento(FuenteFinanciamiento fuefinSiges) {
        if (fuefinSiges != null) {
            org.agesic.siges.visualizador.web.ws.FuenteFinanciamiento fuefinVisua = new org.agesic.siges.visualizador.web.ws.FuenteFinanciamiento();
//            fuefinVisua.setFueCodigo(fuefinSiges.get);
//            fuefinVisua.setFueHabilitado(fuefinSiges.get);
            fuefinVisua.setFuePk(fuefinSiges.getFuePk());
            fuefinVisua.setFueNombre(fuefinSiges.getFueNombre());

            return fuefinVisua;
        }
        return null;
    }

    public static org.agesic.siges.visualizador.web.ws.Organismos convertOrganismos(Organismos orgSiges) {
        if (orgSiges != null) {
            org.agesic.siges.visualizador.web.ws.Organismos orgVisua = new org.agesic.siges.visualizador.web.ws.Organismos();
            orgVisua.setOrgPk(orgSiges.getOrgPk());
            orgVisua.setOrgActivo(orgSiges.getOrgActivo());
            orgVisua.setOrgLogo(orgSiges.getOrgLogo());
            orgVisua.setOrgLogoNombre(orgSiges.getOrgLogoNombre());
            orgVisua.setOrgNombre(orgSiges.getOrgNombre());
            orgVisua.setOrgToken(orgSiges.getOrgToken());

            return orgVisua;
        }
        return null;
    }

    public static Organizaciones convertOrgaIntProve(OrganiIntProve orgaSiges) {
        if (orgaSiges != null) {
            Organizaciones orgaVisua = new Organizaciones();
            orgaVisua.setOrgPk(orgaSiges.getOrgaPk());
            orgaVisua.setOrgDireccion(orgaSiges.getOrgaDireccion());
            orgaVisua.setOrgHabilitado(null);
            orgaVisua.setOrgMail(orgaSiges.getOrgaMail());
            orgaVisua.setOrgNombre(orgaSiges.getOrgaNombre());
            orgaVisua.setOrgProveedor(orgaSiges.getOrgaProveedor());
            orgaVisua.setOrgRazonSocial(orgaSiges.getOrgaRazonSocial());
            orgaVisua.setOrgRut(orgaSiges.getOrgaRut());
            orgaVisua.setOrgTelefono(orgaSiges.getOrgaTelefono());
            orgaVisua.setOrgWeb(orgaSiges.getOrgaWeb());

            return orgaVisua;
        }
        return null;
    }
}
