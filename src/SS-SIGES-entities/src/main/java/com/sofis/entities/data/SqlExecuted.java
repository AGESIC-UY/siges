package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "sql_executed")
@XmlRootElement
public class SqlExecuted implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sql_pk")
    private Integer sqlPk;
    @Column(name = "sql_ver_mayor")
    private Integer sqlVerMayor;
    @Column(name = "sql_ver_menor")
    private Integer sqlVerMenor;
    @Column(name = "sql_build")
    private Integer sqlBuild;
    @Column(name = "sql_update")
    private Integer sqlUpdate;
    @Column(name = "sql_desc")
    private String sqlDesc;
    @Column(name = "sql_fecha")
    @Temporal(TemporalType.DATE)
    private Date sqlFecha;

    public Integer getSqlPk() {
        return sqlPk;
    }

    public void setSqlPk(Integer sqlPk) {
        this.sqlPk = sqlPk;
    }

    public Integer getSqlVerMayor() {
        return sqlVerMayor;
    }

    public void setSqlVerMayor(Integer sqlVerMayor) {
        this.sqlVerMayor = sqlVerMayor;
    }

    public Integer getSqlVerMenor() {
        return sqlVerMenor;
    }

    public void setSqlVerMenor(Integer sqlVerMenor) {
        this.sqlVerMenor = sqlVerMenor;
    }

    public Integer getSqlBuild() {
        return sqlBuild;
    }

    public void setSqlBuild(Integer sqlBuild) {
        this.sqlBuild = sqlBuild;
    }

    public Integer getSqlUpdate() {
        return sqlUpdate;
    }

    public void setSqlUpdate(Integer sqlUpdate) {
        this.sqlUpdate = sqlUpdate;
    }

    public String getSqlDesc() {
        return sqlDesc;
    }

    public void setSqlDesc(String sqlDesc) {
        this.sqlDesc = sqlDesc;
    }

    public Date getSqlFecha() {
        return sqlFecha;
    }

    public void setSqlFecha(Date sqlFecha) {
        this.sqlFecha = sqlFecha;
    }

}
