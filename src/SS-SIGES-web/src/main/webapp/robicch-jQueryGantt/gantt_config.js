/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery.noConflict();
var ge;  //this is the hugly but very friendly global var for the gantt editor

function configGannt() {
//jQuery(function() {
    //load templates
    jQuery("#ganttemplates").loadTemplates();

    // here starts gantt initialization
    ge = new GanttMaster();
    var workSpace = jQuery("#workSpace");
    workSpace.css({width: jQuery(window).width() - 20, height: jQuery(window).height() - 100});
    ge.init(workSpace);
    
    jQuery(".ganttButtonBar div").append("<button onclick='clearGantt();' class='button'>Limpiar</button>");
    jQuery(".ganttButtonBar div").addClass('buttons');

    //overwrite with localized ones
    loadI18n();

    //load de data from server
    //loadGanttFromServer();

    //fill default Teamwork roles if any
    if (!ge.roles || ge.roles.length == 0) {
        setRoles();
    }

    //fill default Resources roles if any
    if (!ge.resources || ge.resources.length == 0) {
        setResource();
    }
//});
}

function loadGanttFromServer(taskId, callback) {
    loadFromLocalStorage();
}


function saveGanttOnServer() {
    //console.log("saveGanttOnServer");
    saveInLocalStorage();
}


//-------------------------------------------  Create some demo data ------------------------------------------------------
function setRoles() {
    ge.roles = [
//        {
//            id: "tmp_1",
//            name: "Project Manager"
//        },
//        {
//            id: "tmp_2",
//            name: "Worker"
//        },
//        {
//            id: "tmp_3",
//            name: "Stakeholder/Customer"
//        }
    ];
}

function setResource() {
    var res = [];
//    for (var i = 1; i <= 10; i++) {
//        res.push({id: "tmp_" + i, name: "Resource " + i});
//    }
    ge.resources = res;
}


function clearGantt() {
    ge.reset();
}

function loadI18n() {
//    GanttMaster.messages = {
//        "CHANGE_OUT_OF_SCOPE": "NO_RIGHTS_FOR_UPDATE_PARENTS_OUT_OF_EDITOR_SCOPE",
//        "START_IS_MILESTONE": "START_IS_MILESTONE",
//        "END_IS_MILESTONE": "END_IS_MILESTONE",
//        "TASK_HAS_CONSTRAINTS": "TASK_HAS_CONSTRAINTS",
//        "GANTT_ERROR_DEPENDS_ON_OPEN_TASK": "GANTT_ERROR_DEPENDS_ON_OPEN_TASK",
//        "GANTT_ERROR_DESCENDANT_OF_CLOSED_TASK": "GANTT_ERROR_DESCENDANT_OF_CLOSED_TASK",
//        "TASK_HAS_EXTERNAL_DEPS": "TASK_HAS_EXTERNAL_DEPS",
//        "GANTT_ERROR_LOADING_DATA_TASK_REMOVED": "GANTT_ERROR_LOADING_DATA_TASK_REMOVED",
//        "ERROR_SETTING_DATES": "ERROR_SETTING_DATES",
//        "CIRCULAR_REFERENCE": "CIRCULAR_REFERENCE",
//        "CANNOT_DEPENDS_ON_ANCESTORS": "CANNOT_DEPENDS_ON_ANCESTORS",
//        "CANNOT_DEPENDS_ON_DESCENDANTS": "CANNOT_DEPENDS_ON_DESCENDANTS",
//        "INVALID_DATE_FORMAT": "INVALID_DATE_FORMAT",
//        "TASK_MOVE_INCONSISTENT_LEVEL": "TASK_MOVE_INCONSISTENT_LEVEL",
//        "GANTT_QUARTER_SHORT": "trim.",
//        "GANTT_SEMESTER_SHORT": "sem."
//    };
    GanttMaster.messages = {
        "CHANGE_OUT_OF_SCOPE": "No tiene permisos para actualizar.",
//        "START_IS_MILESTONE": "Inicio es Hito.",
//        "END_IS_MILESTONE": "Final es Hito.",
        "START_IS_MILESTONE": "La fecha de inicio no puede ser menor a la fecha de inicio del hito.",
        "START_IS_MILESTONE_DEPENDENCIA": "No se puede mover el hito dado que tiene una dependencia superior.",
        "END_IS_MILESTONE": "La fecha de fin no puede ser mayor a la fecha de fin del hito.",
        "TASK_HAS_CONSTRAINTS": "El Entregable esta limitado por:",
        "GANTT_ERROR_DEPENDS_ON_OPEN_TASK": "Error en el Cronograma depende de un Entregable abierto.",
        "GANTT_ERROR_DESCENDANT_OF_CLOSED_TASK": "Error en el Cronograma con un Entregable cerrado.",
        "TASK_HAS_EXTERNAL_DEPS": "El entregable tiene una dependencia externa.",
        "GANTT_ERROR_LOADING_DATA_TASK_REMOVED": "Error en el Cronograma al cargar datos de un Entregable borrado.",
        "ERROR_SETTING_DATES": "Error al cargar fechas.",
        "CIRCULAR_REFERENCE": "Referencia Circular.",
        "CANNOT_DEPENDS_ON_ANCESTORS": "No puede depender de su padre.",
        "CANNOT_DEPENDS_ON_DESCENDANTS": "No puede depender de un hijo.",
        "INVALID_DATE_FORMAT": "Formato de fecha invalido.",
        "TASK_MOVE_INCONSISTENT_LEVEL": "El nivel del Entregables es inconsistente.",
        "GANTT_QUARTER_SHORT": "cuarto ",
        "GANTT_SEMESTER_SHORT": "semestre ",
//        "GANTT_CANNOT_DELETE": "No es posible eliminar el entregable. Es usado en reg. de horas, pagos, colaboradores, productos, documentos, riesgos, calidad, o interesados."
        "GANTT_CANNOT_DELETE": "No es posible eliminar el entregable. Es un entregable padre o es usado en reg. de horas, productos, pagos, colaboradores, riesgos, calidad o interesados.",
        "GANTT_CANNOT_ADD_HITOS_CHILDS": "No es posible ingresar hijos en actividades del tipo hito.",
        "GANTT_CANNOT_CLONE_LEVEL_0": "No es posible clonar el entregable de nivel 0.",
        "GANTT_CANNOT_CLONE_EMPTY": "Debe seleccionar un entregable para poder clonarlo.",
        "GANTT_CANNOT_DELETE_PROGRESS": "No es posible eliminar el entregable, ya que tiene avance cargado.",
        "GANTT_CANNOT_ADD_PRODUCTOS_CHILDS": "No es posible ingresar hijos en actividades que tienen un producto asociado.",
    };
}


//-------------------------------------------  Open a black popup for managing resources. This is only an axample of implementation (usually resources come from server) ------------------------------------------------------
function openResourceEditor() {
    var editor = jQuery("<div>");
    editor.append("<h2>Resource editor</h2>");
    editor.addClass("resEdit");

    for (var i in ge.resources) {
        var res = ge.resources[i];
        var inp = jQuery("<input type='text'>").attr("pos", i).addClass("resLine").val(res.name);
        editor.append(inp).append("<br>");
    }

    var sv = jQuery("<div>save</div>").css("float", "right").addClass("button").click(function() {
        jQuery(this).closest(".resEdit").find("input").each(function() {
            var el = jQuery(this);
            var pos = el.attr("pos");
            ge.resources[pos].name = el.val();
        });
        ge.editor.redraw();
        closeBlackPopup();
    });
    editor.append(sv);

    var ndo = createBlackPage(800, 500).append(editor);
}

//-------------------------------------------  Get project file as JSON (used for migrate project from gantt to Teamwork) ------------------------------------------------------
function getFile() {
    jQuery("#gimBaPrj").val(JSON.stringify(ge.saveProject()));
    jQuery("#gimmeBack").submit();
    jQuery("#gimBaPrj").val("");
}


//-------------------------------------------  LOCAL STORAGE MANAGEMENT (for this demo only) ------------------------------------------------------
Storage.prototype.setObject = function(key, value) {
    this.setItem(key, JSON.stringify(value));
};


Storage.prototype.getObject = function(key) {
    return this.getItem(key) && JSON.parse(this.getItem(key));
};

var ret;

function loadFromLocalStorage() {
    //console.log("loadFromLocalStorage " + JSON.stringify(ret));
    if (ret != null && ret != 1 && ret != undefined ) {
        ge.loadProject(ret);
        ge.checkpoint(); //empty the undo stack
    }
}

function loadGanttValues1(r1) {
    ret = r1;
}
function loadGanttValues2(r2) {
    ret = ret + r2;
    ge.loadProject(ret);
    ge.checkpoint(); //empty the undo stack
}
function loadGanttValues(r) {
    ret = r;
//    //console.log(ret);
    ge.loadProject(ret);
    ge.checkpoint(); //empty the undo stack
}

function saveInLocalStorage() {
    //console.log("saveInLocalStorage");
//    window.url = "http://localhost:8080/proyectoBase-WS/resources";
    var prj = ge.saveProject();
    var localStorage = false;
    if (localStorage) {
        localStorage.setObject("teamworkGantDemo1", prj);
    } else {
//        //console.log("llamando al ws");
        var dataString = JSON.stringify(prj);
//        //console.log("dataString:");
//        //console.log(dataString);

        var iVal = document.getElementById("ficha:dataCrono");
        jQuery(iVal).val(dataString);
        jQuery('.guardarCronBtn').click();
    }
}