function isHorasEstimadas(evt, elem) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 58)) {
	return false;
    }

//    var elem = document.getElementById("horasEstimadas");
    if (charCode == 58) {
	var splitValue = elem.value.split("");
	for (i = 0; i < splitValue.length; i++) {
	    if (splitValue[i] == ":") {
		return false;
	    }
	}
    }

    var splitValue = elem.value.split(":");
    if (splitValue.length == 2 && parseInt(splitValue[1]) >= 6
	    && (charCode > 47 && charCode < 58)) {
	alert("No se puede superar los 59 minutos.");
	return false;
    }

    return true;
}

function validarHorasEstimadas(elem) {
//    var elem = document.getElementById("horasEstimadas");
    var splitValue = elem.value.split("");
    if (splitValue.length > 0) {
	if (splitValue[0] == ":") {
	    elem.value = "0" + elem.value;
	}
    }

    var splitValue = elem.value.split(":");
    if (splitValue.length == 2 && parseInt(splitValue[1]) > 59) {
	alert("No se puede superar los 59 minutos.");
    }
}

jQuery.fn.loadTemplates = function () {
    jQuery.JST.loadTemplates(jQuery(this));
    return this;
};

jQuery.JST = {
    _templates: new Object(),
    _decorators: new Object(),
    loadTemplates: function (elems) {
	elems.each(function () {
	    jQuery(this).find(".__template__").each(function () {
		var tmpl = jQuery(this);
		var type = tmpl.attr("type");


		var typeS = type + "";
		var templateBody = "";

		if ("GANTBUTTONS" == typeS) {
		    templateBody = "<div class=\"ganttButtonBar noprint\">" +
			    "<h1 style=\"float:left\"></h1>" +
			    "<span class=\"ganttButtonSeparator\"></span>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('addAboveCurrentTask.gantt');\" class=\"button textual\" title=\"Insertar encima\" id=\"addAboveBtn\">" +
			    "<span class=\"teamworkIcon\">l</span></button>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('addBelowCurrentTask.gantt');\" class=\"button textual\" title=\"Insertar debajo\" id=\"addBelowBtn\">" +
			    "<span class=\"teamworkIcon\">X</span></button>" +
			    "<span class=\"ganttButtonSeparator\"></span>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('indentCurrentTask.gantt');\" class=\"button textual\" title=\"Indentar adelante\" id=\"indentBtn\">" +
			    "<span class=\"teamworkIcon\">.</span></button>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('outdentCurrentTask.gantt');\" class=\"button textual\" title=\"Indentar atrás\" id=\"outdentBtn\">" +
			    "<span class=\"teamworkIcon\">:</span></button>" +
			    "<span class=\"ganttButtonSeparator\"></span>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('moveUpCurrentTask.gantt');\" class=\"button textual\" title=\"Subir\" id=\"moveUpBtn\">" +
			    "<span class=\"teamworkIcon\">k</span></button>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('moveDownCurrentTask.gantt');\" class=\"button textual\" title=\"Bajar\" id=\"moveDownBtn\">" +
			    "<span class=\"teamworkIcon\">j</span></button>" +
			    "<span class=\"ganttButtonSeparator\"></span>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('zoomMinus.gantt'); return false;\" class=\"button textual\" title=\"Alejar\">" +
			    "<span class=\"teamworkIcon\">)</span></button>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('zoomPlus.gantt'); return false;\" class=\"button textual\" title=\"Acercar\">" +
			    "<span class=\"teamworkIcon\">(</span></button><span class=\"ganttButtonSeparator\"></span>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('copyCurrentTask.gantt');\" class=\"button textual\" title=\"Duplicar\" id=\"copyBtn\">" +
			    "<span class=\"teamworkIcon\">c</span></button><span class=\"ganttButtonSeparator\"></span>" +
			    "<button onclick=\"jQuery('#workSpace').trigger('deleteCurrentTask.gantt');\" class=\"button textual\" title=\"Eliminar\" id=\"deleteBtn\">" +
			    "<span class=\"teamworkIcon\">&cent;</span></button>&nbsp; &nbsp; &nbsp; &nbsp;" +
			    "<button onclick=\"saveGanttOnServer();\" class=\"boton principal guardar\" id=\"guardarBtn\" title=\"Guardar\">Guardar</button></div></div>";
		}
		if ("TASKSEDITHEAD" == typeS) {
		    templateBody = "<table class=\"gdfTable\" cellspacing=\"0\" cellpadding=\"0\"><thead>" +
                        "<tr style=\"height:40px\"><th class=\"gdfColHeader\" style=\"width:8px;\"></th>" +
                        "<th class=\"gdfColHeader \" style=\"width:80px; text-align: center;\">Nombre</th>" +
                        "<th class=\"gdfColHeader \" style=\"width:20px; text-align: center;\">Fecha Inicio</th>" +
                        "<th class=\"gdfColHeader \" style=\"width:20px; text-align: center;\">Fecha Fin</th>" +
//			"<th class=\"gdfColHeader gdfResizable\" style=\"width:20px; text-align: center;\" >Hito</th>" +
                        "<th class=\"gdfColHeader \" style=\"width:20px; text-align: center;\" >Dependencia</th>" +
//			"<th class=\"gdfColHeader gdfResizable\" style=\"width:20px; text-align: center;\" >%</th>" +
//			"<th class=\"gdfColHeader gdfResizable\" style=\"width:100px;\">Horas Est. (hh:mm)</th></tr>" +
                        "</thead></table>";
		}

		if ("TASKROW" == typeS) {
                    templateBody = "<tr taskId=\"(#=obj.id#)\" class=\"taskEditRow (#=obj.isParent()?'isParent':''#) (#=obj.collapsed?'collapsed':''#)\" level=\"(#=level#)\">" +
                        "<th class=\"gdfCell edit\" align=\"right\" style=\"cursor:pointer;\">" +
                        "<span class=\"taskRowIndex\">(#=obj.getRow()+1#)</span>" +
                        "<span class=\"teamworkIcon\" style=\"font-size:11px;\">e</span>"+ 
                        "</th>" +
                        "<td class=\"gdfCell indentCell\" style=\"padding-left:(#=obj.level*10#)px;\">" +
                        "<span type=\"text\" name=\"name\" style=\"(#=obj.level>0?'border-left:2px dotted orange':''#)\">(#=obj.name + \"-\"+ obj.esfuerzo#)</span>"+
                        "</td>" +
                        "<td class=\"gdfCell\"><input type=\"text\" name=\"start\" value=\"\" class=\"date\"></td>" +
                        "<td class=\"gdfCell\"><input type=\"text\" name=\"end\" value=\"\" class=\"date\"></td>" +
//			"<td class=\"gdfCell\" align=\"center\"><input type=\"checkbox\" name=\"endIsMilestone\"></td>" +
			"<td class=\"gdfCell\"><input type=\"text\" name=\"depends\" value=\"(#=obj.depends#)\" (#=obj.hasExternalDep?\"readonly\":\"\"#)></td>" +
//			"<td class=\"gdfCell\"><input type=\"text\" name=\"progress\" class=\"validated\" entrytype=\"PERCENTILE\" autocomplete=\"off\" value=\"(#=obj.progress?obj.progress:''#)\" (#=obj.progressByWorklog?\"readOnly\":\"\"#)></td>" +
//			"<td class=\"gdfCell\"><input type=\"text\" name=\"horasEstimadas\" value=\"(#=obj.horasEstimadas#)\" onkeypress=\"return isHorasEstimadas(event, this);\" onkeyup=\"validarHorasEstimadas(this);\"></td>" +
                        "</tr>";
		}

                if ("TASKEMPTYROW" == typeS) {
                    templateBody = "<tr class=\"taskEditRow emptyRow\" >"
                        + "<th class=\"gdfCell\" align=\"right\"></th>"
                        + "<td class=\"gdfCell\" align=\"center\"></td>"
                        + "<td class=\"gdfCell\"></td>"
                        + "<td class=\"gdfCell\"></td>"
                        + "<td class=\"gdfCell\"></td>"
//			+ "<td class=\"gdfCell\"></td>"
//                      + "<td class=\"gdfCell\"></td>"
                    +"</tr>";

		}

		if ("TASKBAR" == typeS) {
		    templateBody = "<div class=\"taskBox edit\" taskId=\"(#=obj.id#)\" >" +
			    "<div class=\"(#=obj.endIsMilestone ? 'taskMilestone' : 'layout'#) (#=obj.hasExternalDep?'extDep':''#)\">" +
			    "<div class=\"taskProgress (#=obj.progress > 100 ? 'taskProgressRed' : 'taskProgressBlue'#)\" style=\"height:(#=obj.endIsMilestone ? '16px' : '90%'#); width:(#=obj.progress > 100 ? 100 : obj.progress#)%;\">" +
			    "<span class=\"(#=obj.parent ? 'parentTaskBar' : ''#)\" style=\"color: white;\">(#=obj.parent || obj.endIsMilestone ? '' : obj.progress#)</span>" +
			    "</div>" +
			    "<div class=\"taskProgress1 (#=obj.parent? 'parentTaskBar' : (obj.end < new Date()) ? 'taskProgressRed' : new Date(new Date(obj.end).setHours(0,0,0,0)) >= new Date(new Date(new Number(document.getElementById('ficha:diaHoraServer_input').value)).setHours(0,0,0,0)) ? 'taskProgressGreen' : 'taskProgressRed' #)\" style=\"float:right; height:(#=obj.endIsMilestone ? '16px' : '100%'#); width:(#=obj.progress > 100 ? 0 : 100-obj.progress#)%;\">" +
			    "</div>" +
// mgarcia -Se comenta porque originalmente genera una marca milestone para inicio y fin.
//                            "<div class=\"milestone (#=obj.startIsMilestone?'active':''#)\" ></div>" +
//                            "<div class=\"taskLabel\"></div>" +
//                            "<div class=\"milestone end (#=obj.endIsMilestone?'active':''#)\" ></div>" +
			    "</div>" +
			    "</div>";
		}

		if ("CHANGE_STATUS" == typeS) {
		    templateBody = "<div class=\"taskStatusBox\"><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_ACTIVE\" title=\"active\"></div><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_DONE\" title=\"completed\"></div><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_FAILED\" title=\"failed\"></div><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_SUSPENDED\" title=\"suspended\"></div><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_UNDEFINED\" title=\"undefined\"></div></div>";
		}

		// Popup para editar los datos de una task.
		if ("TASK_EDITOR" == typeS) {
		    templateBody = "	<?xml version=\"1.0\" encoding=\"UTF-8\"?>	" +
			    "	<div class=\"ganttTaskEditor icePnlPop\">	" +
			    "	   <table width=\"100%\">	" +
			    "	      <tbody>	" +
			    "	         <tr>	" +
			    "	            <td class=\"icePnlPopHdr\">	" +
			    "	               <span class=\"iceOutTxt\">Editar entregable</span>	" +
			    "	            </td>	" +
			    "	         </tr>	" +
			    "	         <tr>	" +
			    "	            <td class=\"icePnlPopBody row\">	" +
			    "	               <div class=\"col-sm-10 no-padding formulario\" style=\"margin-bottom: 21px;\">	" +
			    "	                  <label for=\"name\" class=\"col-sm-1\" style=\"margin-right: 17px;margin-top: 5px;\">Nombre</label>	" +
			    "	                  <input type=\"text\" name=\"name\" id=\"name\" value=\"\" size=\"35\" class=\"formElements col-sm-10\" autofocus=\"\" style=\"height: 24px;\" />	" +
			    "	               </div>	" +
			    "	               <div class=\"col-sm-2 no-padding formulario\" style=\"margin-bottom: 21px;\">	" +
			    "	                     <input type=\"checkbox\" id=\"endIsMilestone\" name=\"endIsMilestone\" />	" +
			    "	                     <label for=\"endIsMilestone\">HITO</label> " +
			    "	               </div>	" +
			    "	               <div class=\"col-sm-12 no-padding formulario\">	" +
			    "	                  <div class=\"col-sm-6\">	" +
			    "	                     <div>	" +
			    "	                        <label for=\"coordinador\">Coordinador</label>	" +
			    "	                        <select id=\"coordinador\" name=\"coordinador\" class=\"formElements\" style=\"width: 332px;margin-left: 9px;\">	" +
			    "	                           <option value=\"-1\" />	" +
			    "	                        </select>	" +
			    "	                     </div>	" +
			    "	                  </div>	" +
			    "	                  <div class=\"col-sm-5 no-padding\">	" +
			    "	                     <input type=\"checkbox\" id=\"cambiarCoordHijos\" name=\"cambiarCoordHijos\" />	" +
			    "	                     <label for=\"cambiarCoordHijos\">Cambiar el coordinador de sus hijos.</label>	" +
			    "	                  </div>	" +
			    "	                  <div class=\"col-sm-12 formulario bordeSup\">	" +
			    "	                     <div class=\"col-sm-12\" style=\"/* margin-left: -16px; */\">	" +
			    "	                        <span>Planificaci\u00F3n</span>	" +
			    "	                     </div>	" +
			    "	                     <!-- Planificacion -->	" +
			    "	                     <div id=\"esfuerzoDiv\" style=\" margin-left: 31px;\">	" +
			    "	                        <label for=\"esfuerzo\">Esfuerzo</label>	" +
			    "	                        <input type=\"text\" name=\"esfuerzo\" id=\"esfuerzo\" value=\"\" size=\"3\" class=\"formElements dateInput\" />	" +
			    "	                     </div>	" +
			    "	                     <div id=\"startPlanDiv\">	" +
			    "	                        <label id=\"start_plan_label\" for=\"start_plan\">Inicio</label>	" +
			    "	                        <input type=\"text\" name=\"start_plan\" id=\"start_plan\" value=\"\" class=\"date formElements dateInput\" size=\"10\" />	" +
			    "	                     </div>	" +
			    "	                     <div id=\"endPlanDiv\">	" +
			    "	                        <label id=\"end_plan_label\" for=\"end_plan\">Fin</label>	" +
			    "	                        <input type=\"text\" name=\"end_plan\" id=\"end_plan\" value=\"\" class=\"date formElements dateInput\" size=\"10\" />	" +
			    "	                     </div>	" +
			    "	                     <div id=\"planDuracionDias\" style=\"padding-top: 5px;\">	" +
			    "	                        <span id=\"planDuracionDiasSpan\">Duraci\u00F3n: X d\u00EDas</span>	" +
			    "	                        <input type=\"text\" id=\"duration_plan\" name=\"duration_plan\" style=\"display:none\" />	" +
			    "	                     </div>	" +
			    "	                     <div id=\"horasEstimadasDiv\" style=\"float: right;\">	" +
			    "	                        <label for=\"horasEstimadas\">Horas Estimadas</label>	" +
			    "	                        <input type=\"text\" name=\"horasEstimadas\" id=\"horasEstimadas\" value=\"\" class=\"formElements dateInput\" size=\"3\" onkeypress=\"return isHorasEstimadas(event, this);\" onkeyup=\"validarHorasEstimadas(this);\" />	" +
			    "	                     </div>	" +
			    "	                  </div>	" +
                            "	                  <div id=\"inicioPeriodoDivRow\" class=\"col-sm-12 formulario\">	" +
			    "	                     <div id=\"inicioPeriodoDiv\" style=\" margin-left: 31px;\">	" +
			    "	                        <input type=\"checkbox\" name=\"periodo_inicio\" id=\"periodo_inicio\"/>	" +
                            "	                        <label for=\"periodo_inicio\">Inicio del Proyecto</label>	" +
			    "	                     </div>	" +
                            "	                     <div id=\"finPeriodoDiv\" style=\" margin-left: 31px;\">	" +
			    "	                        <input type=\"checkbox\" name=\"periodo_fin\" id=\"periodo_fin\"/>	" +
                            "	                        <label for=\"periodo_fin\">Fin del Proyecto</label>	" +
			    "	                     </div>	" +
			    "	                  </div>	" +
			    "	                  <div class=\"col-sm-12 formulario bordeSup\" style=\"\">	" +
			    "	                     <div class=\"col-sm-12\" style=\"/* margin-left: -16px; */\">	" +
			    "	                        <span>Ejecuci\u00F3n</span>	" +
			    "	                     </div>	" +
			    "	                     <div style=\"width: 172px;\" />	" +
			    "	                     <!-- Ejecucion -->	" +
			    "	                     <div id=\"startEjecDiv\" style=\" margin-left: 18px;\">	" +
			    "	                        <label id=\"start_ejec_label\" for=\"start_ejec\">Inicio</label>	" +
			    "	                        <input type=\"text\" name=\"start_ejec\" id=\"start_ejec\" value=\"\" class=\"date formElements dateInput\" size=\"10\" />	" +
			    "	                     </div>	" +
			    "	                     <div id=\"endEjecDiv\">	" +
			    "	                        <label id=\"end_ejec_label\" for=\"end_ejec\">Fin</label>	" +
			    "	                        <input type=\"text\" name=\"end_ejec\" id=\"end_ejec\" value=\"\" class=\"date formElements dateInput\" size=\"10\" />	" +
			    "	                     </div>	" +
			    "	                     <div id=\"ejecDuracionDias\" style=\"padding-top: 5px;\">	" +
			    "	                        <span id=\"ejecDuracionDiasSpan\">Duraci\u00F3n: X d\u00EDas</span>	" +
			    "	                        <input type=\"text\" id=\"duration_ejec\" name=\"duration_ejec\" style=\"display:none\" />	" +
			    "	                     </div>	" +
			    "	                     <div id=\"progressDiv\" style=\"float: right; margin-right:50px;\">	" +
			    "	                        <label for=\"progress\">% Avance</label>	" +
			    "	                        <input id=\"progress\" name=\"progress\" class=\"formElements\" />	" +
			    "	                     </div>	" +
			    "	                  </div>	" +
			    "	                  <div class=\"col-sm-12 formulario bordeSup\" style=\"padding-top: 17px;\">	" +
			    "	                     <div class=\"col-sm-7\" style=\"\">	" +
			    "	                        <label for=\"description\" style=\"vertical-align: top;\">Observaciones</label>	" +
			    "	                        <textarea rows=\"5\" cols=\"30\" id=\"description\" name=\"description\" class=\"formElements\" style=\" width: 362px;\" />	" +
			    "	                     </div>	" +
			    "	                     <div class=\"col-sm-4\">	" +
			    "	                        <div>	" +
			    "	                           <input type=\"checkbox\" id=\"relevantePMO\" name=\"relevantePMO\" />	" +
			    "	                           <label for=\"relevantePMO\">Entregable/Hito Clave (solo PMO)</label>	" +
			    "	                        </div>	" +
			    "	                        <div>	" +
			    "	                           <input type=\"text\" name=\"tieneProductos\" id=\"tieneProductos\" value=\"\" class=\"formElements\" disabled=\"disabled\" />	" +
			    "	                        </div>	" +
			    "	                     </div>	" +
			    "	                     <div id=\"taskDiv\" class=\"col-sm-12 formulario\">	" +
			    "	                        <div style=\"text-align: right; padding-top: 20px; clear: both; float:right;\">	" +
			    "	                           <button id=\"saveButton\" class=\"button big\">Confirmar</button>	" +
			    "	                           <a id=\"closeEditor\" onclick=\"closeEditorDiv();\" name=\"closeEditor\">Cerrar</a>	" +
			    "	                        </div>	" +
			    "	                     </div>	" +
			    "	                     <script type=\"text/javascript\">" +
			    "			    $(\"#relevantePMO\").change(function(){ " +
			    "					if($(this).is(\":checked\")){" +
			    "					     $(\"label[for='relevantePMO']\").css({'color': 'red'}); " +
			    "					}else{ " +
			    "					    $(\"label[for='relevantePMO']\").css({'color': '#808080'}); " +
			    "					}}); " +
			    "				    $(\"#endIsMilestone\").change(function(){ " +
			    "					if($(this).is(\":checked\")){" +
			    "						$(\"#startPlanDiv\").hide(); " +
			    "						$(\"#startEjecDiv\").hide(); " +
                            "						$(\"#endEjecDiv\").css({'margin-left':'18px'}); " +
			    "						$(\"#planDuracionDias\").hide(); " +
			    "						$(\"#ejecDuracionDias\").hide(); " +
			    "					}else{ " +
			    "						$(\"#startPlanDiv\").show();" +
			    "						$(\"#startEjecDiv\").show(); " +
			    "						$(\"#planDuracionDias\").show(); " +
			    "						$(\"#ejecDuracionDias\").show(); " +
                            "						$(\"#endEjecDiv\").css({'margin-left':''}); " +
			    "					} " +
//			    "    }" +
			    "				    });" +
//                            "                        if($(\"#endIsMilestone\").is(\":checked\")){ $(\"#endEjecDiv\").css({'margin-left':'18px'}); }           " +
			    "			    </script>	" +
			    "	                  </div>	" +
			    "	               </div>	" +
			    "	            </td>	" +
			    "	         </tr>	" +
			    "	      </tbody>	" +
			    "	   </table>	" +
			    "	</div>	";
		}

		if ("ASSIGNMENT_ROW" == typeS) {
		    templateBody = "<div class=\"ganttButtonBar noprint\"><h1 style=\"float:left\"></h1><div class=\"buttons\"><button onclick=\"jQuery('#workSpace').trigger('undo.gantt');\" class=\"button textual\" title=\"undo\"><span class=\"teamworkIcon\">&#39;</span></button><button onclick=\"jQuery('#workSpace').trigger('redo.gantt');\" class=\"button textual\" title=\"redo\"><span class=\"teamworkIcon\">&middot;</span></button><span class=\"ganttButtonSeparator\"></span><button onclick=\"jQuery('#workSpace').trigger('addAboveCurrentTask.gantt');\" class=\"button textual\" title=\"insert above\"><span class=\"teamworkIcon\">l</span></button><button onclick=\"jQuery('#workSpace').trigger('addBelowCurrentTask.gantt');\" class=\"button textual\" title=\"insert below\"><span class=\"teamworkIcon\">X</span></button><span class=\"ganttButtonSeparator\"></span><button onclick=\"jQuery('#workSpace').trigger('indentCurrentTask.gantt');\" class=\"button textual\" title=\"indent task\"><span class=\"teamworkIcon\">.</span></button><button onclick=\"jQuery('#workSpace').trigger('outdentCurrentTask.gantt');\" class=\"button textual\" title=\"unindent task\"><span class=\"teamworkIcon\">:</span></button><span class=\"ganttButtonSeparator\"></span><button onclick=\"jQuery('#workSpace').trigger('moveUpCurrentTask.gantt');\" class=\"button textual\" title=\"move up\"><span class=\"teamworkIcon\">k</span></button><button onclick=\"jQuery('#workSpace').trigger('moveDownCurrentTask.gantt');\" class=\"button textual\" title=\"move down\"><span class=\"teamworkIcon\">j</span></button><span class=\"ganttButtonSeparator\"></span><button onclick=\"jQuery('#workSpace').trigger('zoomMinus.gantt');\" class=\"button textual\" title=\"zoom out\"><span class=\"teamworkIcon\">)</span></button><button onclick=\"jQuery('#workSpace').trigger('zoomPlus.gantt');\" class=\"button textual\" title=\"zoom in\"><span class=\"teamworkIcon\">(</span></button><span class=\"ganttButtonSeparator\"></span><button onclick=\"jQuery('#workSpace').trigger('deleteCurrentTask.gantt');\" class=\"button textual\" title=\"delete\"><span class=\"teamworkIcon\">&cent;</span></button>&nbsp; &nbsp; &nbsp; &nbsp;<button onclick=\"saveGanttOnServer();\" class=\"button first big\" title=\"save\">Guardar</button></div></div>";
		}

		//template may be inside <!-- ... --> or not in case of ajax loaded templates

		/*if (tmpl.get(0).firstChild != null && tmpl.get(0).firstChild.nodeType == 8) // 8==comment
		 var templateBody = tmpl.get(0).firstChild.nodeValue; // this is inside the comment
		 else
		 var templateBody = tmpl.html(); // this is the whole template
		 */

		//alert(templateBody)

		if (!templateBody.match(/##\w+##/)) { // is Resig' style? e.g. (#=id#) or (# ...some javascript code 'obj' is the alias for the object #)
		    var strFunc =
			    "var p=[],print=function(){p.push.apply(p,arguments);};" +
			    "with(obj){p.push('" +
			    templateBody.replace(/[\r\t\n]/g, " ")
			    .replace(/'(?=[^#]*#\))/g, "\t")
			    .split("'").join("\\'")
			    .split("\t").join("'")
			    .replace(/\(#=(.+?)#\)/g, "',$1,'")
			    .split("(#").join("');")
			    .split("#)").join("p.push('")
			    + "');}return p.join('');";

		    try {
			jQuery.JST._templates[type] = new Function("obj", strFunc);
		    } catch (e) {
			console.error("JST error: " + type, e, strFunc);
		    }

		} else { //plain template   e.g. ##id##
		    try {
			jQuery.JST._templates[type] = templateBody;
		    } catch (e) {
			console.error("JST error: " + type, e, templateBody);
		    }
		}

		tmpl.remove();

	    });
	});
    },
    createFromTemplate: function (jsonData, template, transformToPrintable) {
	var templates = jQuery.JST._templates;

	var jsData = new Object();
	if (transformToPrintable) {
	    for (var prop in jsonData) {
		var value = jsonData[prop];
		if (typeof (value) == "string")
		    value = (value + "").replace(/\n/g, "<br>");
		jsData[prop] = value;
	    }
	} else {
	    jsData = jsonData;
	}


	function fillStripData(strip, data) {
	    for (var prop in data) {
		var value = data[prop];

		strip = strip.replace(new RegExp("##" + prop + "##", "gi"), value);
	    }
	    // then clean the remaining ##xxx##
	    strip = strip.replace(new RegExp("##\\w+##", "gi"), "");
	    return strip;
	}

	var stripString = "";
	if (typeof (template) == "undefined") {
	    alert("Template is required");
	    stripString = "<div>Template is required</div>";

	} else if (typeof (templates[template]) == "function") { // resig template
	    try {
		stripString = templates[template](jsData);// create a jquery object in memory
	    } catch (e) {
		console.error("JST error: " + template, e.message);
		stripString = "<div> ERROR: " + template + "<br>" + e.message + "</div>";
	    }

	} else {
	    stripString = templates[template]; // recover strip template
	    if (!stripString || stripString.trim() == "") {
		console.error("No template found for type '" + template + "'");
		return jQuery("<div>");

	    } else {
		stripString = fillStripData(stripString, jsData); //replace placeholders with data
	    }
	}

	var ret = jQuery(stripString);// create a jquery object in memory
	ret.attr("__template", template); // set __template attribute

	//decorate the strip
	var dec = jQuery.JST._decorators[template];
	if (typeof (dec) == "function")
	    dec(ret, jsData);

	return ret;
    },
    existsTemplate: function (template) {
	return jQuery.JST._templates[template];
    },
    //decorate function is like function(domElement,jsonData){...}
    loadDecorator: function (template, decorator) {
	jQuery.JST._decorators[template] = decorator;
    },
    getDecorator: function (template) {
	return jQuery.JST._decorators[template];
    },
    decorateTemplate: function (element) {
	var dec = jQuery.JST._decorators[element.attr("__template")];
	if (typeof (dec) == "function")
	    dec(editor);
    },
    // asynchronous
    ajaxLoadAsynchTemplates: function (templateUrl, callback) {

	jQuery.get(templateUrl, function (data) {

	    var div = jQuery("<div>");
	    div.html(data);

	    jQuery.JST.loadTemplates(div);

	    if (typeof (callback == "function"))
		callback();
	}, "html");
    },
    ajaxLoadTemplates: function (templateUrl) {
	jQuery.ajax({
	    async: false,
	    url: templateUrl,
	    dataType: "html",
	    success: function (data) {
		var div = jQuery("<div>");
		div.html(data);
		jQuery.JST.loadTemplates(div);
	    }
	});
    }
};
