package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.DocFile;
import com.sofis.entities.data.Documentos;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class DocFileDao extends HibernateJpaDAOImp<DocFile, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DocFileDao.class.getName());
    
    public DocFileDao(EntityManager em) {
        super(em);
    }

    public List<DocFile> obtenerHistoricoDocFile(Documentos d) throws DAOGeneralException {
//        String queryString = "select "
//                + " max(df.docfile_pk), \n"
//                + " max(df.docfile_nombre), \n"
//                + " max(df.docfile_path), \n"
//                + " max(df.docfile_ult_mod), \n"
//                + " max(df.docfile_ult_usuario), \n"
//                + " max(df.docfile_version), \n"
//                + " max(df.docfile_doc_fk)\n"
//                + " from\n"
//                + "   aud_doc_file df  INNER JOIN\n"
//                + "   doc_file df_2 ON (df.docfile_pk = df_2.docfile_pk)"
//                + " where df.docfile_doc_fk = :doc_pk AND "
//                + "       df.REVTYPE <> 2 AND\n"
//                + "       df.docfile_path <> df_2.docfile_path\n"
//                + " group by df.docfile_path\n"
//                + " order by df.docfile_ult_mod desc";

        String queryString = "select \n"
                + "	df.docfile_pk,\n"
                + "	df.docfile_nombre,\n"
                + "	df.docfile_path,\n"
                + "	df.docfile_ult_mod,\n"
                + "	df.docfile_ult_usuario,\n"
                + "	df.docfile_version,\n"
                + "	df.docfile_doc_fk,\n"
                + "	df.REV\n"
                + "from\n"
                + "	aud_doc_file df  INNER JOIN\n"
                + "	doc_file df_2 ON (df.docfile_pk = df_2.docfile_pk)\n"
                + "where \n"
                + "	df.docfile_doc_fk = :doc_pk AND\n"
                + "	df.REVTYPE <> 2 AND\n"
                + "	df.docfile_path <> df_2.docfile_path AND\n"
                + "	not exists(\n"
                + "		select 1\n"
                + "        from aud_doc_file adf \n"
                + "        where\n"
                + "			adf.docfile_doc_fk = df.docfile_doc_fk AND\n"
                + "			adf.REVTYPE <> 2 AND\n"
                + "			adf.docfile_path = df.docfile_path AND\n"
                + "			adf.docfile_ult_mod > df.docfile_ult_mod AND\n"
                + "            adf.REV <> df.REV\n"
                + "    )\n"
                + "order by df.docfile_ult_mod desc";
        
        
        Query q = this.getEm().createNativeQuery(queryString);
        q.setParameter("doc_pk", d.getDocsPk());
        List<Object[]> queryResult = q.getResultList();
        List<DocFile> res = new LinkedList<>();
        DocFile dfAux = null;
        String filename;
        String[] aux = new String[2];
        if (queryResult != null && !queryResult.isEmpty()) {
            Integer version = queryResult.size();
            for (Object[] tupla : queryResult) {
                dfAux = new DocFile();
                dfAux.setDocfileNombre((String) tupla[1]);
                dfAux.setDocfilePath((String) tupla[2]);
                dfAux.setDocfileUltMod(new Date(((java.sql.Timestamp) tupla[3]).getTime()));
                dfAux.setDocfileUltUsuario((String) tupla[4]);
                dfAux.setRev((Integer) tupla[7]);
                dfAux.setDocfileVersion(version);
                dfAux.setDocfilePk((Integer) tupla[0]);
                /**
                 * Seteo el nombre con la version
                 */
                aux = dfAux.getDocfileFileNameExtension();
                filename = aux[0] + "_" + version + "." + aux[1];
                dfAux.setDocfileNombreHist(filename);

                res.add(dfAux);
                version--;
            }
        }
        return res;
    }

    public List<DocFile> obtenerDocFilePorProg(Integer progPk) {
        String queryStr = "SELECT df.docfile_pk FROM doc_file AS df"
                + " INNER JOIN documentos AS doc"
                + " ON df.docfile_doc_fk = doc.docs_pk"
                + " INNER JOIN prog_docs AS pd"
                + " ON doc.docs_pk = pd.progdocs_doc_pk"
                + " INNER JOIN programas AS prog"
                + " ON prog.prog_pk = pd.progdocs_prog_pk"
                + " WHERE prog.prog_pk = :progPk";

        Query query = super.getEm().createNativeQuery(queryStr);
        query.setParameter("progPk", progPk);

        List<Integer> docId = query.getResultList();

        if (docId == null || docId.isEmpty()) {
            return null;
        }

        String queryStr2 = "Select df from DocFile df"
                + " where df.docfilePk in (:ids)";

        Query query2 = super.getEm().createQuery(queryStr2);
        query2.setParameter("ids", docId);

        List<DocFile> listDocFile = query2.getResultList();
        return listDocFile;
    }

    public List<DocFile> obtenerDocFilePorProy(Integer proyPk) {
        String queryStr = "SELECT df.docfile_pk FROM doc_file AS df"
                + " INNER JOIN documentos AS doc"
                + " ON df.docfile_doc_fk = doc.docs_pk"
                + " INNER JOIN proy_docs AS pd"
                + " ON doc.docs_pk = pd.proydoc_doc_pk"
                + " INNER JOIN proyectos AS proy"
                + " ON proy.proy_pk = pd.proydoc_proy_pk"
                + " WHERE proy.proy_pk = :proyPk";

        Query query = super.getEm().createNativeQuery(queryStr);
        query.setParameter("proyPk", proyPk);

//	List<DocFile> resultado = query.getResultList();
        List<Integer> docId = query.getResultList();

        if (docId == null || docId.isEmpty()) {
            return null;
        }

        String queryStr2 = "Select df from DocFile df"
                + " where df.docfilePk in (:ids)";

        Query query2 = super.getEm().createQuery(queryStr2);
        query2.setParameter("ids", docId);

        List<DocFile> listDocFile = query2.getResultList();
        return listDocFile;
    }

    public byte[] obtenerDocFile(Integer docFilePk) {
        String queryStr = "SELECT docfile_file FROM doc_file AS df"
                + " WHERE docfile_pk = :docFilePk";

        Query query = super.getEm().createNativeQuery(queryStr);
        query.setParameter("docFilePk", docFilePk);

        List<byte[]> resultado = query.getResultList();
        byte[] fileDoc = resultado != null ? resultado.get(0) : null;
        return fileDoc;
    }

    public boolean procesarMoverDocFS() {
        try {
            String queryStr = "select docfile_pk from doc_file"
                    + " where docfile_path is not null limit 1";

            Query query = super.getEm().createNativeQuery(queryStr);

            List<Integer> resultado = query.getResultList();

            return resultado == null || resultado.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public int eliminarDocFiles() {
        String queryStr = "update doc_file set docfile_file = null where docfile_file is not null and docfile_pk > 0";
        Query query = super.getEm().createNativeQuery(queryStr);
        int result = query.executeUpdate();

        return result;
    }

    public int removerAuditoria() {
        String queryStr = "delete from aud_doc_file where docfile_pk > 0";
        Query query = super.getEm().createNativeQuery(queryStr);
        int result = query.executeUpdate();

        return result;
    }
}
