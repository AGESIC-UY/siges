/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.sofisform.to;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jdom.Element;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class FormSearchTO extends ComponentTO implements Serializable{

    //el message bundle de donde se deben de tomar los mensajes
    private String messagebundle;
    //el modelo asociado a la busqueda
    private ModelTO model;
    //la definicion de la busqueda
    private CriteriaTO searchdefinition;
    //el contenedor donde se incluye la tabla resultado
    private ComponentTO containerSearchResult;
    //las columnas a incluir en la tabla resultado
    private List<ComponentTO> searchresult;
    //las acciones que se incorporan al final de la tabla
    private List<ComponentTO> actionsSearchResult;
    // los componentes para crear el panel de busqueda
    private List<ComponentTO> searchpanel;
    // los componentes para crear el action Panel
    private List<ComponentTO> actionPanel;
    private PaginatorTO paginator;
    //los parametros adicionales del search result, son los parametros a incluid en el datatable
    private HashMap<String,String> searchResultParamValues = new HashMap();
    //la ordenacion de la busqueda
    private String orderBy;
    //el boolean de si la ordenacion es ascendente
    private boolean asc;
    //si el search result debe de incorporar el preview de la entidad
    private boolean preview = false;
    //si el search result debe de incorporar el activar desactivar el preview
    private boolean previewActivate = false;
    //si debe de generar checkbox para multiple selecortes
    private boolean multipleselector = false;
    //si se debe de generar el dataExporter para el resultado de la búsqueda
    private boolean dataExporter = false;
    private String dataExporterType ="excel";
    private String dataExporterImage="";
    private String dataExporterHeaderFooterBean="";
    private String dataExporterHeaderFooterMethod="";

    public String getDataExporterHeaderFooterBean() {
        return dataExporterHeaderFooterBean;
    }

    public void setDataExporterHeaderFooterBean(String dataExporterHeaderFooterBean) {
        this.dataExporterHeaderFooterBean = dataExporterHeaderFooterBean;
    }

    public String getDataExporterHeaderFooterMethod() {
        return dataExporterHeaderFooterMethod;
    }

    public void setDataExporterHeaderFooterMethod(String dataExporterHeaderFooterMethod) {
        this.dataExporterHeaderFooterMethod = dataExporterHeaderFooterMethod;
    }




    public String getDataExporterImage() {
        return dataExporterImage;
    }

    public void setDataExporterImage(String dataExporterImage) {
        this.dataExporterImage = dataExporterImage;
    }



    public String getDataExporterType() {
        return dataExporterType;
    }

    public void setDataExporterType(String dataExporterType) {
        this.dataExporterType = dataExporterType;
    }



    public boolean isDataExporter() {
        return dataExporter;
    }

    public void setDataExporter(boolean dataExporter) {
        this.dataExporter = dataExporter;
    }

   



    public boolean isMultipleselector() {
        return multipleselector;
    }

    public void setMultipleselector(boolean multipleselector) {
        this.multipleselector = multipleselector;
    }



    public boolean isPreviewActivate() {
        return previewActivate;
    }

    public void setPreviewActivate(boolean previewActivate) {
        this.previewActivate = previewActivate;
    }


    
    public boolean isPreview() {
        return preview;
    }

    public void setPreview(boolean preview) {
        this.preview = preview;
    }


    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    


    public HashMap<String, String> getSearchResultParamValues() {
        return searchResultParamValues;
    }

    public void setSearchResultParamValues(HashMap<String, String> searchResultParamValues) {
        this.searchResultParamValues = searchResultParamValues;
    }

    public ComponentTO getContainerSearchResult() {
        return containerSearchResult;
    }

    public void setContainerSearchResult(ComponentTO containerSearchResult) {
        this.containerSearchResult = containerSearchResult;
    }

    public PaginatorTO getPaginator() {
        return paginator;
    }

    public void setPaginator(PaginatorTO paginator) {
        this.paginator = paginator;
    }


    
    public List<ComponentTO> getActionsSearchResult() {
        return actionsSearchResult;
    }

    public void setActionsSearchResult(List<ComponentTO> actionsSearchResult) {
        this.actionsSearchResult = actionsSearchResult;
    }



    public String getMessagebundle() {
        return messagebundle;
    }

    public void setMessagebundle(String messagebundle) {
        this.messagebundle = messagebundle;
    }

    public ModelTO getModel() {
        return model;
    }

    public void setModel(ModelTO model) {
        this.model = model;
    }

    public CriteriaTO getSearchdefinition() {
        return searchdefinition;
    }

    public void setSearchdefinition(CriteriaTO searchdefinition) {
        this.searchdefinition = searchdefinition;
    }

    public List<ComponentTO> getSearchpanel() {
        return searchpanel;
    }

    public void setSearchpanel(List<ComponentTO> searchpanel) {
        this.searchpanel = searchpanel;
    }

    public List<ComponentTO> getSearchresult() {
        return searchresult;
    }

    public void setSearchresult(List<ComponentTO> searchresult) {
        this.searchresult = searchresult;
    }

     public void addColumn(ColumnTO f){
        if (searchresult == null){
            searchresult = new ArrayList<ComponentTO>();
        }
        searchresult.add(f);
    }

    public List<ComponentTO> getActionPanel() {
        return actionPanel;
    }

    public void setActionPanel(List<ComponentTO> actionPanel) {
        this.actionPanel = actionPanel;
    }

    @Override
    public Element toXML() {
        Element toReturn = new Element("formsearch");
        toReturn.setAttribute("key", this.getKey() + "");
        toReturn.setAttribute("messageBundle", this.getMessagebundle() + "");
        if (this.getModel() != null) {
            Element modelElement = this.getModel().toXML();
            toReturn.getChildren().add(modelElement);
        }

        //LOS ACTIONS
        List<ComponentTO> actionsComps = this.getActionPanel();
        Element actionsElement = new Element("actions");
        if (actionsComps != null){
            for (ComponentTO comp : this.getActionPanel()) {
                actionsElement.getChildren().add(comp.toXML());
            }
        }
        toReturn.getChildren().add(actionsElement);

        //LOS SearchResult
        Element searchResultElement = new Element("searchresult");
        Element searchResultColumnsElement = new Element("columns");
        searchResultElement.getChildren().add(searchResultColumnsElement);
        Element searchResultActionsElement = new Element("columnactions");
        List<ComponentTO> searchResultComp = this.getSearchresult();
        for(ComponentTO comp : searchResultComp){
            if (comp instanceof ColumnTO){
                searchResultColumnsElement.getChildren().add(comp.toXML());
            }
        }
        List<ComponentTO>  actionsSearchResul= this.getActionsSearchResult();
        for(ComponentTO comp : actionsSearchResul){
            if (comp instanceof ActionTO){
                searchResultActionsElement.getChildren().add(comp.toXML());
            }
        }
        searchResultColumnsElement.getChildren().add(searchResultActionsElement);

        //el paginator del search result
        searchResultElement.setAttribute("paginator",paginator.isPaginator() +"");
        searchResultElement.setAttribute("databasePaginator",paginator.isDatabasePaginator() +"");
        searchResultElement.setAttribute("fastStep",paginator.getFastStep() +"");
        searchResultElement.setAttribute("paginatorMaxPages",paginator.getPaginatorMaxPages() +"");
        searchResultElement.setAttribute("rows",paginator.getRows() +"");
        searchResultElement.setAttribute("nextUrl",paginator.getNextUrl() +"");
        searchResultElement.setAttribute("nextTitle",paginator.getNextTitle() +"");
        searchResultElement.setAttribute("nextStyle",paginator.getNextStyle() +"");
        searchResultElement.setAttribute("previousUrl",paginator.getPreviousUrl() +"");
        searchResultElement.setAttribute("previousTitle",paginator.getPreviousTitle() +"");
        searchResultElement.setAttribute("previousStyle",paginator.getPreviousStyle() +"");
        searchResultElement.setAttribute("lastUrl",paginator.getLastUrl() +"");
        searchResultElement.setAttribute("lastTitle",paginator.getLastTitle() +"");
        searchResultElement.setAttribute("lastStyle",paginator.getLastStyle() +"");
        searchResultElement.setAttribute("firstUrl",paginator.getFirstUrl() +"");
        searchResultElement.setAttribute("firstTitle",paginator.getFirstTitle() +"");
        searchResultElement.setAttribute("firstStyle",paginator.getFirstStyle() +"");
        searchResultElement.setAttribute("fastForwardUrl",paginator.getFastForwardUrl() +"");
        searchResultElement.setAttribute("fastForwardTitle",paginator.getFastForwardTitle() +"");
        searchResultElement.setAttribute("fastForwardStyle",paginator.getFastForwardStyle() +"");
        searchResultElement.setAttribute("fastRewindUrl",paginator.getFastRewindUrl() +"");
        searchResultElement.setAttribute("fastRewindTitle",paginator.getFastRewindTitle() +"");
        searchResultElement.setAttribute("fastRewindStyle",paginator.getFastRewindStyle() +"");
        searchResultElement.setAttribute("preview",this.preview + "");
        searchResultElement.setAttribute("previewActivate",this.previewActivate + "");
        searchResultElement.setAttribute("dataExporterImage",this.dataExporterImage + "");
        searchResultElement.setAttribute("dataExporterType",this.dataExporterType + "");
        searchResultElement.setAttribute("dataExporter",this.dataExporter + "");
        searchResultElement.setAttribute("multipleselector",this.multipleselector + "");

        //los param values del search result
        List<Element> paramsValuesElement =this.searchResultParamValuesToXML();
        for (Element paramVlaue: paramsValuesElement){
            searchResultElement.getChildren().add(paramVlaue);
        }
        toReturn.getChildren().add(searchResultElement);

        //EL SEARCH DEFINITION
        CriteriaTO criteriaTO = this.getSearchdefinition();
        Element searchDefinitionElement = new Element("searchdefinition");
        if (criteriaTO != null){
            searchDefinitionElement.getChildren().add(criteriaTO.toXML());
        }

        if (this.orderBy == null){
            searchDefinitionElement.setAttribute("orderBy","");
        }else{
            searchDefinitionElement.setAttribute("orderBy",this.orderBy);
        }

        searchDefinitionElement.setAttribute("asc",this.asc + "");
        toReturn.getChildren().add(searchDefinitionElement);
        

        //EL SEARCH PANEL
        List<ComponentTO> searchPanelComps = this.getSearchpanel();
        Element searchpanelElement = new Element("searchpanel");
        Element componentsElement = new Element("components");
        searchpanelElement.getChildren().add(componentsElement);

        for (ComponentTO comp: searchPanelComps){
            componentsElement.getChildren().add(comp.toXML());
        }
        toReturn.getChildren().add(searchpanelElement);




        return toReturn;
    }

    private List<Element> searchResultParamValuesToXML(){
        List<Element> paramValuesList = new ArrayList();
        if (this.getSearchResultParamValues() != null && this.getSearchResultParamValues().size() > 0){
            for (String keyParam : this.getSearchResultParamValues().keySet()){
                String value = this.getSearchResultParamValues().get(keyParam);
                 Element paramValueE = new Element("paramvalue");
                 paramValueE.setAttribute("param", keyParam);
                 paramValueE.setAttribute("value", value);
                 paramValuesList.add(paramValueE);
            }
        }
        return paramValuesList;
    }



}
