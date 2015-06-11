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

jQuery.fn.loadTemplates = function() {
    jQuery.JST.loadTemplates(jQuery(this));
    return this;
};

jQuery.JST = {
    _templates: new Object(),
    _decorators: new Object(),
    loadTemplates: function(elems) {
        elems.each(function() {
            jQuery(this).find(".__template__").each(function() {
                var tmpl = jQuery(this);
                var type = tmpl.attr("type");


                var typeS = type + "";
                var templateBody = "";

                if ("GANTBUTTONS" == typeS) {
                    templateBody = "<div class=\"ganttButtonBar noprint\">" +
                            "<h1 style=\"float:left\"></h1>" +
                            "<span class=\"ganttButtonSeparator\"></span>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('addAboveCurrentTask.gantt');\" class=\"button textual\" title=\"insert above\" id=\"addAboveBtn\">" +
                            "<span class=\"teamworkIcon\">l</span></button>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('addBelowCurrentTask.gantt');\" class=\"button textual\" title=\"insert below\" id=\"addBelowBtn\">" +
                            "<span class=\"teamworkIcon\">X</span></button>" +
                            "<span class=\"ganttButtonSeparator\"></span>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('indentCurrentTask.gantt');\" class=\"button textual\" title=\"indent task\" id=\"indentBtn\">" +
                            "<span class=\"teamworkIcon\">.</span></button>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('outdentCurrentTask.gantt');\" class=\"button textual\" title=\"unindent task\" id=\"outdentBtn\">" +
                            "<span class=\"teamworkIcon\">:</span></button>" +
                            "<span class=\"ganttButtonSeparator\"></span>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('moveUpCurrentTask.gantt');\" class=\"button textual\" title=\"move up\" id=\"moveUpBtn\">" +
                            "<span class=\"teamworkIcon\">k</span></button>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('moveDownCurrentTask.gantt');\" class=\"button textual\" title=\"move down\" id=\"moveDownBtn\">" +
                            "<span class=\"teamworkIcon\">j</span></button>" +
                            "<span class=\"ganttButtonSeparator\"></span>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('zoomMinus.gantt'); return false;\" class=\"button textual\" title=\"zoom out\">" +
                            "<span class=\"teamworkIcon\">)</span></button>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('zoomPlus.gantt'); return false;\" class=\"button textual\" title=\"zoom in\">" +
                            "<span class=\"teamworkIcon\">(</span></button><span class=\"ganttButtonSeparator\"></span>" +
                            "<button onclick=\"jQuery('#workSpace').trigger('deleteCurrentTask.gantt');\" class=\"button textual\" title=\"delete\" id=\"deleteBtn\">" +
                            "<span class=\"teamworkIcon\">&cent;</span></button>&nbsp; &nbsp; &nbsp; &nbsp;" +
                            "<button onclick=\"saveGanttOnServer();\" class=\"button first big\" id=\"guardarBtn\" title=\"Guardar\">Guardar</button></div></div>";
                }
                if ("TASKSEDITHEAD" == typeS) {
                    templateBody = "<table class=\"gdfTable\" cellspacing=\"0\" cellpadding=\"0\"><thead>" +
                            "<tr style=\"height:40px\"><th class=\"gdfColHeader\" style=\"width:35px;\"></th>" +
                            "<th class=\"gdfColHeader gdfResizable\" style=\"width:250px;\">Nombre</th>" +
                            "<th class=\"gdfColHeader gdfResizable\" style=\"width:95px;\">Fecha Inicio</th>" +
                            "<th class=\"gdfColHeader gdfResizable\" style=\"width:90px;\">Fecha Fin</th>" +
                            "<th class=\"gdfColHeader gdfResizable\" style=\"width:100px;\">Dependencia.</th>" +
                            "<th class=\"gdfColHeader gdfResizable\" style=\"width:100px;\">Horas Est. (hh:mm)</th></tr>" +
                            "</thead></table>";
                }

                if ("TASKROW" == typeS) {
                    templateBody = "<tr taskId=\"(#=obj.id#)\" class=\"taskEditRow\" level=\"(#=level#)\">" +
                            "<th class=\"gdfCell edit\" align=\"right\" style=\"cursor:pointer;\">" +
                            "<span class=\"taskRowIndex\">(#=obj.getRow()+1#)</span>" +
                            "<span class=\"teamworkIcon\" style=\"font-size:12px;\">e</span></th>" +
                            "<td class=\"gdfCell indentCell\" style=\"padding-left:(#=obj.level*10#)px;\">" +
                            "<span type=\"text\" name=\"name\" style=\"(#=obj.level>0?'border-left:2px dotted orange':''#)\">(#=obj.name + \"-\"+ obj.esfuerzo#)</span></td>" +
                            "<td class=\"gdfCell\"><input type=\"text\" name=\"start\" value=\"\" class=\"date\"></td>" +
                            "<td class=\"gdfCell\"><input type=\"text\" name=\"end\" value=\"\" class=\"date\"></td>" +
                            "<td class=\"gdfCell\"><input type=\"text\" name=\"depends\" value=\"(#=obj.depends#)\" (#=obj.hasExternalDep?\"readonly\":\"\"#)></td>" +
                            "<td class=\"gdfCell\"><input type=\"text\" name=\"horasEstimadas\" value=\"(#=obj.horasEstimadas#)\" onkeypress=\"return isHorasEstimadas(event, this);\" onkeyup=\"validarHorasEstimadas(this);\"></td>" +
                            "</tr>";
                }

                if ("TASKEMPTYROW" == typeS) {
                    templateBody = "<tr class=\"taskEditRow emptyRow\" >"
                            +"<th class=\"gdfCell\" align=\"right\"></th>"
                            +"<td class=\"gdfCell\" align=\"center\"></td>"
                            +"<td class=\"gdfCell\"></td>"
                            +"<td class=\"gdfCell\"></td>"
                            +"<td class=\"gdfCell\"></td>"
                            +"<td class=\"gdfCell\"></td>"
                            +"<td class=\"gdfCell\">"
                            +"</td><td class=\"gdfCell\"></td></tr>";
                }

                if ("TASKBAR" == typeS) {
                    templateBody = "<div class=\"taskBox edit\" taskId=\"(#=obj.id#)\" >" +
                            "<div class=\"layout (#=obj.hasExternalDep?'extDep':''#)\">" +
                            "<div class=\"taskProgress (#=obj.progress>100 ? 'taskProgressRed' : 'taskProgressBlue'#)\" style=\"height:90%; width:(#=obj.progress>100?100:obj.progress#)%;\">" +
                            "<span class=\"(#=obj.parent ? 'parentTaskBar' : ''#)\" style=\"color: white;\">(#=obj.parent ? '' : obj.progress#)</span>" +
                            "</div>" +
                            "<div class=\"taskProgress1 (#=obj.parent? 'parentTaskBar' : (obj.end > obj.endLineaBase && obj.endLineaBase > 0 && obj.endLineaBase > 93599999) ? 'taskProgressRed' : obj.end > document.getElementById('ficha:diaHoraServer_input').value ? 'taskProgressGreen' : 'taskProgressRed'#)\" style=\"float:right; height:100%; width:(#=obj.progress>100?0:100-obj.progress#)%;\">" +
                            "</div>" +
                            "<div class=\"milestone (#=obj.startIsMilestone?'active':''#)\" ></div>" +
                            "<div class=\"taskLabel\"></div>" +
                            "<div class=\"milestone end (#=obj.endIsMilestone?'active':''#)\" ></div>" +
                            "</div>" +
                            "</div>";
                }

                if ("CHANGE_STATUS" == typeS) {
                    templateBody = "<div class=\"taskStatusBox\"><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_ACTIVE\" title=\"active\"></div><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_DONE\" title=\"completed\"></div><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_FAILED\" title=\"failed\"></div><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_SUSPENDED\" title=\"suspended\"></div><div class=\"taskStatus cvcColorSquare\" status=\"STATUS_UNDEFINED\" title=\"undefined\"></div></div>";
                }

                // Popup para editar los datos de una task.
                if ("TASK_EDITOR" == typeS) {
                    templateBody = "<div class=\"ganttTaskEditor icePnlPop\">" +
                            "<table width=\"100%\">" +
                            "<tr><td class=\"icePnlPopHdr\"><span class=\"iceOutTxt\">Editar entregable</span></td></tr>" +
                            "<tr>" +
                            "<td class=\"icePnlPopBody row\">" +
                            "<div class=\"col-sm-12 no-padding formulario\">" +
                            "<div>" +
                            "<label for=\"name\" class=\"col-sm-12\">Nombre</label>" +
                            "<input type=\"text\" name=\"name\" id=\"name\" value=\"\" size=\"35\" class=\"formElements col-sm-12\" autofocus>" +
                            "</div>" +
                            "</div>" +
                            "<div class=\"col-sm-5 no-padding formulario\">" +
                            "<div>" +
                            "<label for=\"description\" class=\"col-sm-12\">Observaciones</label>" +
                            "<textarea rows=\"5\" cols=\"30\" id=\"description\" name=\"description\" class=\"formElements\"></textarea>" +
                            "</div>" +
                            "<div>" +
                            "<label for=\"coordinador\" class=\"col-sm-12\">Coordinador</label>" +
                            "<select id=\"coordinador\" name=\"coordinador\" class=\"formElements\"><option value=\"-1\"></option></select>" +
                            "</div>" +
                            "<div>" +
                            "<input type=\"checkbox\" id=\"cambiarCoordHijos\" name=\"cambiarCoordHijos\"> Cambiar el coordinador de sus hijos.</input>" +
                            "</div>" +
                            "<input type=\"text\" name=\"tieneProductos\" id=\"tieneProductos\" value=\"\" class=\"formElements\" disabled='disabled'>" +
                            "</div>" +
                            "<div class=\"col-sm-7 no-padding\">" +
                            "<div class=\"col-sm-6 no-padding-left formulario\">" +
                            "<div id=\"esfuerzoDiv\">" +
                            "<label for=\"esfuerzo\" class=\"col-sm-12\">Esfuerzo</label>" +
                            "" +
                            "<input type=\"text\" name=\"esfuerzo\" id=\"esfuerzo\" value=\"\" size=\"3\" class=\"formElements\">" +
                            "</div>" +
                            "<div>" +
                            "<label for=\"start\" class=\"col-sm-12\">Fecha Inicio</label>" +
                            "<input type=\"text\" name=\"start\" id=\"start\" value=\"\" class=\"date formElements\" size=\"10\"><br>" +
                            //"<input type=\"checkbox\" id=\"startIsMilestone\">"+
                            "</div>" +
                            "</div>" +
                            "<div class=\"col-sm-6 no-padding-right formulario\">" +
                            "<div id=\"progressDiv\">" +
                            "<label for=\"progress\" class=\"col-sm-12\">% Avance</label>" +
                            "<select id=\"progress\" name=\"progress\" class=\"formElements\"></select><br>" +
                            "</div>" +
                            "<div>" +
                            "<label for=\"end\" class=\"col-sm-12\">Fecha Fin</label>" +
                            "<input type=\"text\" name=\"end\" id=\"end\" value=\"\" class=\"date formElements\" size=\"10\"><br>" +
                            //"<input type=\"checkbox\" id=\"endIsMilestone\">"+
                            "</div>" +
                            "</div>" +
                            "<div class=\"col-sm-12 no-padding formulario\">" +
                            "<div>" +
                            "<label for=\"end\" class=\"col-sm-12\">Horas Estimadas</label>" +
                            "<input type=\"text\" name=\"horasEstimadas\" id=\"horasEstimadas\" value=\"\" class=\"formElements col-sm-12\" size=\"3\" onkeypress=\"return isHorasEstimadas(event, this);\" onkeyup=\"validarHorasEstimadas(this);\"><br>" +
                            "</div>" +
                            "<div>" +
                            "<input type=\"checkbox\" id=\"relevantePMO\" name=\"relevantePMO\"> Entregable Clave (solo PMO)</input>" +
                            "</div>" +
                            "</div>" +
                            "</div>" +
                            "<div style=\"text-align: right; padding-top: 20px; clear: both;\">" +
                            "<button id=\"saveButton\" class=\"button big\">Confirmar</button></div>" +
                            "</td>" +
                            "</tr>" +
                            "</table>" +
                            "</div>";
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
    createFromTemplate: function(jsonData, template, transformToPrintable) {
        var templates = jQuery.JST._templates;

        var jsData = new Object();
        if (transformToPrintable) {
            for (var prop in jsonData) {
                var value = jsonData[prop];
                if (typeof(value) == "string")
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
        if (typeof(template) == "undefined") {
            alert("Template is required");
            stripString = "<div>Template is required</div>";

        } else if (typeof(templates[template]) == "function") { // resig template
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
    existsTemplate: function(template) {
        return jQuery.JST._templates[template];
    },
    //decorate function is like function(domElement,jsonData){...}
    loadDecorator: function(template, decorator) {
        jQuery.JST._decorators[template] = decorator;
    },
    getDecorator: function(template) {
        return jQuery.JST._decorators[template];
    },
    decorateTemplate: function(element) {
        var dec = jQuery.JST._decorators[element.attr("__template")];
        if (typeof (dec) == "function")
            dec(editor);
    },
    // asynchronous
    ajaxLoadAsynchTemplates: function(templateUrl, callback) {

        jQuery.get(templateUrl, function(data) {

            var div = jQuery("<div>");
            div.html(data);

            jQuery.JST.loadTemplates(div);

            if (typeof(callback == "function"))
                callback();
        }, "html");
    },
    ajaxLoadTemplates: function(templateUrl) {
        jQuery.ajax({
            async: false,
            url: templateUrl,
            dataType: "html",
            success: function(data) {
                var div = jQuery("<div>");
                div.html(data);
                jQuery.JST.loadTemplates(div);
            }
        });
    }
};
