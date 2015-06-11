package com.sofis.entities.utils;

import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroInicioItem;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class SsUsuariosUtils {

    public static List<SsUsuario> sortByNombreApellido(List<SsUsuario> listUsuarios) {
        if (CollectionsUtils.isNotEmpty(listUsuarios)) {
            Collections.sort(listUsuarios, new Comparator<SsUsuario>() {
                @Override
                public int compare(SsUsuario u1, SsUsuario u2) {
                    if (u1 != null && u1.getNombreApellido() != null && u2 != null && u2.getNombreApellido() != null) {
                        return u1.getNombreApellido().compareTo(u2.getNombreApellido());
                    }
                    return 0;
                }
            });
        }
        return listUsuarios;
    }

    /**
     * Dada la lista de usuarios retorna un array de sus mails.
     *
     * @param list
     * @return
     */
    public static String[] arrayMailsUsuarios(List<SsUsuario> list) {
        if (list != null && !list.isEmpty()) {
            String[] arrUsuarios = new String[list.size()];
            int ind = 0;
            for (SsUsuario usu : list) {
                arrUsuarios[ind++] = usu.getUsuCorreoElectronico();
            }
            return arrUsuarios;
        }
        return null;
    }

    public static boolean isUsuarioPMO(Object ficha, SsUsuario usuario, Integer orgPk) {
        boolean isPmot = isUsuarioPMOT(usuario, orgPk);
        boolean isPmof = isUsuarioPMOF(ficha, usuario, orgPk);

        if (isPmot || isPmof) {
            return true;
        }
        return false;
    }

    public static boolean isUsuarioPMOT(SsUsuario usuario, Integer orgPk) {
        return usuario.isRol(SsRolCodigos.PMO_TRANSVERSAL, orgPk);
    }

    /**
     *
     * @param ficha Puede ser FichaTO, FiltroInicioItem, Programas o Proyectos.
     * @param usuario
     * @param orgPk
     * @return
     */
    public static boolean isUsuarioPMOF(Object ficha, SsUsuario usuario, Integer orgPk) {
        SsUsuario usuarioPMOF = null;

        if (ficha instanceof FichaTO) {
            usuarioPMOF = ((FichaTO) ficha).getUsrPmofedFk();
        } else if (ficha instanceof FiltroInicioItem) {
            usuarioPMOF = new SsUsuario(((FiltroInicioItem) ficha).getPmofId());
        } else if (ficha instanceof Proyectos) {
            usuarioPMOF = ((Proyectos) ficha).getProyUsrPmofedFk();
        } else if (ficha instanceof Programas) {
            usuarioPMOF = ((Programas) ficha).getProgUsrPmofedFk();
        }

        return usuarioPMOF != null
                && usuario.getUsuId().equals(usuarioPMOF.getUsuId())
                && (usuario.isUsuarioPMOF(orgPk) || usuario.isUsuarioPMOT(orgPk));
    }

    public static boolean isUsuarioComun(Object ficha, SsUsuario usuario, Integer orgPk) {
        return !(isUsuarioGerenteOAdjuntoFicha(ficha, usuario)
                || usuario.isUsuarioPMOT(orgPk)
                || isUsuarioPMOF(ficha, usuario, orgPk)
                || usuario.isUsuarioDirector(orgPk));
    }

    /**
     *
     * @param ficha Puede ser FichaTO, FiltroInicioItem, Programas o Proyectos.
     * @param usuario
     * @return
     */
    public static boolean isUsuarioSponsorFicha(Object ficha, SsUsuario usuario) {
        SsUsuario usuGerente = null;

        if (ficha != null) {
            if (ficha instanceof FichaTO) {
                usuGerente = ((FichaTO) ficha).getUsrSponsorFk();
            } else if (ficha instanceof FiltroInicioItem) {
                usuGerente = new SsUsuario(((FiltroInicioItem) ficha).getSponsorId());
            } else if (ficha instanceof Proyectos) {
                usuGerente = ((Proyectos) ficha).getProyUsrSponsorFk();
            } else if (ficha instanceof Programas) {
                usuGerente = ((Programas) ficha).getProgUsrSponsorFk();
            }
        }

        return usuGerente != null
                && usuario.getUsuId().equals(usuGerente.getUsuId());
    }
    
    /**
     *
     * @param ficha Puede ser FichaTO, FiltroInicioItem, Programas o Proyectos.
     * @param usuario
     * @return
     */
    public static boolean isUsuarioGerenteFicha(Object ficha, SsUsuario usuario) {
        SsUsuario usuGerente = null;

        if (ficha != null) {
            if (ficha instanceof FichaTO) {
                usuGerente = ((FichaTO) ficha).getUsrGerenteFk();
            } else if (ficha instanceof FiltroInicioItem) {
                usuGerente = new SsUsuario(((FiltroInicioItem) ficha).getResponsableId());
            } else if (ficha instanceof Proyectos) {
                usuGerente = ((Proyectos) ficha).getProyUsrGerenteFk();
            } else if (ficha instanceof Programas) {
                usuGerente = ((Programas) ficha).getProgUsrGerenteFk();
            }
        }

        return usuGerente != null
                && usuario.getUsuId().equals(usuGerente.getUsuId());
    }

    /**
     *
     * @param ficha Puede ser FichaTO, FiltroInicioItem, Programas o Proyectos.
     * @param usuario
     * @return
     */
    public static boolean isUsuarioAdjuntoFicha(Object ficha, SsUsuario usuario) {
        SsUsuario usuAdjunto = null;

        if (ficha != null) {
            if (ficha instanceof FichaTO) {
                usuAdjunto = ((FichaTO) ficha).getUsrAdjuntoFk();
            } else if (ficha instanceof FiltroInicioItem) {
                usuAdjunto = new SsUsuario(((FiltroInicioItem) ficha).getAdjuntoId());
            } else if (ficha instanceof Proyectos) {
                usuAdjunto = ((Proyectos) ficha).getProyUsrAdjuntoFk();
            } else if (ficha instanceof Programas) {
                usuAdjunto = ((Programas) ficha).getProgUsrAdjuntoFk();
            }
        }

        return usuAdjunto != null
                && usuario.getUsuId().equals(usuAdjunto.getUsuId());
    }

    public static boolean isUsuarioGerenteOAdjuntoFicha(Object ficha, SsUsuario usuario) {
        return isUsuarioGerenteFicha(ficha, usuario) || isUsuarioAdjuntoFicha(ficha, usuario);
    }
}
