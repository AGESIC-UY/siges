package com.sofis.business.utils;

import com.sofis.entities.data.TipoDocumentoInstancia;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoInstanciaUtils {

    public static List<TipoDocumentoInstancia> sortByDocumentosEstado(List<TipoDocumentoInstancia> listTdi, final boolean desc) {
        if (listTdi != null && !listTdi.isEmpty()) {
            Collections.sort(listTdi, new Comparator<TipoDocumentoInstancia>() {
                @Override
                public int compare(TipoDocumentoInstancia tdi1, TipoDocumentoInstancia tdi2) {
                    if (tdi1 != null && tdi1.getDocsEstado() != null && tdi2 != null && tdi2.getDocsEstado() != null) {
                        if (!desc) {
                            return tdi1.getDocsEstado().compareTo(tdi2.getDocsEstado());
                        } else {
                            return tdi2.getDocsEstado().compareTo(tdi1.getDocsEstado());
                        }
                    }
                    return 0;
                }
            });
        }

        return listTdi;
    }

    public static List<TipoDocumentoInstancia> sortByDocumentosNombre(List<TipoDocumentoInstancia> listTdi, final boolean desc) {
        if (listTdi != null && !listTdi.isEmpty()) {
            Collections.sort(listTdi, new Comparator<TipoDocumentoInstancia>() {
                @Override
                public int compare(TipoDocumentoInstancia tdi1, TipoDocumentoInstancia tdi2) {
                    if (tdi1 != null && tdi1.getTipodocInstTipoDocFk() != null && tdi2 != null && tdi2.getTipodocInstTipoDocFk() != null) {
                        if (!desc) {
                            return tdi1.getTipodocInstTipoDocFk().getTipodocNombre().compareTo(tdi2.getTipodocInstTipoDocFk().getTipodocNombre());
                        } else {
                            return tdi2.getTipodocInstTipoDocFk().getTipodocNombre().compareTo(tdi1.getTipodocInstTipoDocFk().getTipodocNombre());
                        }
                    }
                    return 0;
                }
            });
        }

        return listTdi;
    }
}
