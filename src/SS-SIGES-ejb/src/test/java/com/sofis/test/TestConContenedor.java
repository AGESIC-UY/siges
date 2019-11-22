package com.sofis.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupStrategy;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolvedArtifact;

/**
 * Test para a ejecutar dentro de un contenedor.
 * 
 * @author pablo
 */
@Cleanup(phase = TestExecutionPhase.BEFORE, strategy = CleanupStrategy.STRICT)
public abstract class TestConContenedor {
    /**
     * Manejador de entidades de JPA inyectado por el contenedor.
     */
    @PersistenceContext(unitName = "SS-SIGES-PU-test")
    protected EntityManager manejador;
    
    /**
     * Transacción inyectada por el contenedor.
     */
    @Inject
    protected UserTransaction transaccion;
    
    /**
     * 
     * @return 
     */
    @Deployment
    public static WebArchive createArchiveAndDeploy() {
        final List<File> dependencias = new ArrayList<>();
        for (
                final MavenResolvedArtifact dependencia : Maven
                        .configureResolver().workOffline()
                        .loadPomFromFile("pom.xml").importRuntimeDependencies()
                        .resolve().withTransitivity().asResolvedArtifact()
        ) {
            if (!dependencia.getCoordinate().getArtifactId().equals("antlr")) {
                dependencias.add(dependencia.asFile());
            }
        }
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackages(true, "com.sofis.test")
            .addPackages(true, "com.sofis.business.ejbs")
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource("META-INF/beans.xml", "beans.xml")
            .addAsWebInfResource("test-ds.xml", "test-ds.xml")
            .addAsLibraries(dependencias.toArray(new File[0]));
    } 
   
    /**
     * Inicia una transacción.
     * 
     * @throws Exception si algo sale mal.
     */
    protected void iniciarTransaccion() throws Exception {
        this.transaccion.begin();
        this.manejador.joinTransaction();
    }
    
    /**
     * Termina la transacción actual.
     * 
     * @throws Exception si algo sale mal.
     */
    protected void terminarTransaccion() throws Exception {
        this.transaccion.commit();
        this.manejador.clear();
    } 
}
