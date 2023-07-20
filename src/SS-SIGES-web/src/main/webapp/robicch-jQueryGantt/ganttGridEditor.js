/*
 Copyright (c) 2012-2013 Open Lab
 Written by Roberto Bicchierai and Silvia Chelazzi http://roberto.open-lab.com
 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:
 
 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

function GridEditor(master) {
    this.master = master; // is the a GantEditor instance
    var gridEditor = $.JST.createFromTemplate({}, "TASKSEDITHEAD");
    gridEditor.gridify();
    this.element = gridEditor;
}


function daydiff(first, second) {
    return Math.round((second - first) / (1000 * 60 * 60 * 24));
}

function parseDate(str) {
    var mdy = str.split('/');
    return new Date(mdy[2], mdy[0] - 1, mdy[1]);
}


GridEditor.prototype.fillEmptyLines = function (moreRows) {
    var factory = new TaskFactory();
    ////console.debug("GridEditor.fillEmptyLines");
//    var rowsToAdd = 30 - this.element.find(".taskEditRow").size();
// Sofis- Se modifica para obtenerlo de los datos que vienen desde la ficha.
    var rowsToAdd = this.master.rowsGantt - this.element.find(".taskEditRow").size();
    //fill with empty lines
    for (var i = 0; i < rowsToAdd; i++) {
        var emptyRow = $.JST.createFromTemplate({}, "TASKEMPTYROW");
        //click on empty row create a task and fill above
        var master = this.master;
        //la variable moreRows define si se pueden agregar nuevos entregables o no al cronograma
        if (moreRows) {
            emptyRow.click(function (ev) {
                master.beginTransaction();
                var emptyRow = $(this);
                var lastTask;
                var start = new Date().getTime();
                var level = 0;
                if (master.tasks[0]) {
                    start = master.tasks[0].start;
                    level = master.tasks[0].level + 1;
                }

                //fill all empty previouses
                emptyRow.prevAll(".emptyRow").andSelf().each(function () {
                    var ch = factory.build("tmp_fk" + new Date().getTime(), "", "", level, start, 1, null, master.usuarioId);
                    var task = master.addTask(ch);
                    lastTask = ch;
                });
                master.endTransaction();
                lastTask.rowElement.click();
                lastTask.rowElement.find("[name=name]").focus()//focus to "name" input
                        .blur(function () { //if name not inserted -> undo -> remove just added lines
                            var imp = $(this);
                            if (imp.val() == "") {
                                master.undo();
                            }
                        });
            });
        }

        this.element.append(emptyRow);
    }
};
function setParentTask(task, tasks) {
    var pos = tasks.indexOf(task);
    if (pos < tasks.length - 1 && tasks[pos + 1].level > task.level) {
        task.parent = true;
    }
}

GridEditor.prototype.addTask = function (task, row) {
////console.debug("GridEditor.addTask",task,row);
//var prof = new Profiler("editorAddTaskHtml");

//remove extisting row
    this.element.find("[taskId=" + task.id + "]").remove();
    setParentTask(task, this.master.tasks);
    var taskRow = $.JST.createFromTemplate(task, "TASKROW");
    //save row element on task
    task.rowElement = taskRow;
    this.bindRowEvents(task, taskRow);
    if (typeof (row) != "number") {
        var emptyRow = this.element.find(".emptyRow:first"); //tries to fill an empty row
        if (emptyRow.size() > 0)
            emptyRow.replaceWith(taskRow);
        else
            this.element.append(taskRow);
    } else {
        var tr = this.element.find("tr.taskEditRow").eq(row);
        if (tr.size() > 0) {
            tr.before(taskRow);
        } else {
            this.element.append(taskRow);
        }

    }
    this.element.find(".taskRowIndex").each(function (i, el) {
        $(el).html(i + 1);
    });
    //prof.stop();

    return taskRow;
};
GridEditor.prototype.refreshTaskRow = function (task, coordinadores, master) {
    ////console.debug("refreshTaskRow")
    //var profiler = new Profiler("editorRefreshTaskRow");
    var row = task.rowElement;
    row.find(".taskRowIndex").html(task.getRow() + 1);
    row.find(".indentCell").css("padding-left", task.level * 20);
    /*INICIO ampliacion SOFIS - SIGES 1.0*/
    row.find("[name=name]").each(function (i, el) {

        var coordName = "";
        if (coordinadores != null && coordinadores != undefined) {
            for (var i = 0; i < coordinadores.length; i++) {
                var arrSplit = coordinadores[i].split(":");
                if (task.coordinador == arrSplit[0]) {
                    coordName = arrSplit[1];
                }
            }
        }

        var subtitulo = (task.tieneProd ? '(P)' : '') + (task.esReferencia ? '(R)' : '');
        if (task.parent) {

            if (task.id == 1) {
                subtitulo += "<b> " + task.name + "</b><br><b>Esfuerzo T.</b>: " + master.esfuerzoTotal + " - <b>Horas T.</b>: " + master.horasTotal;
            } else {
                subtitulo += "<b> " + task.name + "</b><br>Coordinador: " + coordName;
            }
        } else {
            subtitulo += "(" + task.esfuerzo + ")<b> " + task.name + "</b> " + "<br>Coordinador: " + coordName;
        }

        $(el).html(subtitulo);

    });

    /*FIN ampliacion SOFIS - SIGES 1.0*/

    row.find("[name=code]").val(task.code);
    row.find("[status]").attr("status", task.status);
    row.find("[name=duration]").val(task.duration);
    row.find("[name=start]").val(new Date(task.start).format()).updateOldValue(); // called on dates only because for other field is called on focus event
    row.find("[name=end]").val(new Date(task.end).format()).updateOldValue();
    row.find("[name=depends]").val(task.depends);
    row.find("[name=horasEstimadas]").val(task.horasEstimadas);
    row.find("[name=endIsMilestone]").val(task.endIsMilestone);
    row.find("[name=progress]").val(task.progress);
    row.find(".taskAssigs").html(task.getAssigsString());

    if (task.esReferencia) {
        row.find("[name=start]").attr("disabled", true);
        row.find("[name=end]").attr("disabled", true);
        row.find("[name=depends]").attr("disabled", true);
        ;
    }
    //profiler.stop();
};
GridEditor.prototype.redraw = function () {
    for (var i = 0; i < this.master.tasks.length; i++) {
        this.refreshTaskRow(this.master.tasks[i], this.master.coordinadores, this.master);
    }
};
GridEditor.prototype.reset = function () {
    this.element.find("[taskId]").remove();
};
GridEditor.prototype.bindRowEvents = function (task, taskRow) {
    var self = this;
    var isCoord = (task.coordinador == this.master.usuarioId);
    var isPmoEditor = this.master.isPMOEditor;
    console.log('isPmoEditor bindRowEvents ' + isPmoEditor);

    var editar = this.master.canWrite && (this.master.isPM || isCoord);

    console.log('editar en bindRowEvents ' + editar);
    if (editar || isPmoEditor) {
        console.log('entra a bloque 1');
        self.bindRowInputEvents(task, taskRow);
    } else { //cannot write: disable input
        console.log('entra a bloque 2');
        taskRow.find("input").attr("readonly", true);
        var elem = taskRow.find(".teamworkIcon");
    }

    taskRow.find(".edit").click(function () {
        self.openFullEditor(task, taskRow)
    });
};
GridEditor.prototype.bindRowInputEvents = function (task, taskRow) {
    var self = this;
    //bind dateField on dates
    taskRow.find(".date").each(function () {
        var el = $(this);
        el.click(function () {
            var inp = $(this);
            inp.dateField({
                inputField: el
            });
        });
        el.blur(function (date) {
            var inp = $(this);
            if (inp.isValueChanged()) {
                if (!Date.isValid(inp.val())) {
//		    alert(GanttMaster.messages["INVALID_DATE_FORMAT"]);
                    $('#ganttDialogUtilsMsgDiv').text(GanttMaster.messages["INVALID_DATE_FORMAT"]);
                    ganttDialogUtils.show();
                    inp.val(inp.getOldValue());
                } else {
                    var date = Date.parseString(inp.val());
                    var row = inp.closest("tr");
                    var taskId = row.attr("taskId");
                    var task = self.master.getTask(taskId);
                    var lstart = task.start;
                    var lend = task.end;
                    if (inp.attr("name") == "start") {
                        lstart = date.getTime();
                        if (lstart >= lend) {
                            var end_as_date = new Date(lstart);
                            lend = end_as_date.add('d', task.duration).getTime();
                        }

                        //update task from editor
                        self.master.beginTransaction();
                        self.master.moveTask(task, lstart);
                        self.master.endTransaction();
                    } else {
                        var end_as_date = new Date(date.getTime());
                        lend = end_as_date.getTime();
                        if (lstart >= lend) {
                            end_as_date.add('d', -1 * task.duration);
                            lstart = end_as_date.getTime();
                        }



                        //update task from editor
                        self.master.beginTransaction();
                        self.master.changeTaskDates(task, lstart, lend);
                        self.master.endTransaction();
                        //console.log("end_as_date2:" + end_as_date);
                        //console.log("lstart2:" + lstart);
                        //console.log("lend2:" + lend);
                    }


                    inp.updateOldValue(); //in order to avoid multiple call if nothing changed
                }
            }
        });
    });
    //binding on blur for task update (date exluded as click on calendar blur and then focus, so will always return false, its called refreshing the task row)
    taskRow.find("input:not(.date)").focus(function () {
        $(this).updateOldValue();
    }).blur(function () {
        var el = $(this);
        if (el.isValueChanged()) {
            var row = el.closest("tr");
            var taskId = row.attr("taskId");
            var task = self.master.getTask(taskId);
            //update task from editor
            var field = el.attr("name");
            self.master.beginTransaction();
            if (field == "depends") {
                var oldDeps = task.depends;
                task.depends = el.val();
                // update links
                var linkOK = self.master.updateLinks(task);
                if (linkOK) {
                    //synchronize status fro superiors states
                    var sups = task.getSuperiors();
                    for (var i = 0; i < sups.length; i++) {
                        if (!sups[i].from.synchronizeStatus())
                            break;
                    }

                    self.master.changeTaskDates(task, task.start, task.end);
                }

            } else if (field == "duration") {
                var dur = task.duration;
                dur = parseInt(el.val()) || 1;
                el.val(dur);
                var newEnd = computeEndByDuration(task.start, dur);
                self.master.changeTaskDates(task, task.start, newEnd);
            } else {
                task[field] = el.val();
            }
            self.master.endTransaction();
        }
    });
    //change status
    taskRow.find(".taskStatus").click(function () {
        var el = $(this);
        var tr = el.closest("[taskId]");
        var taskId = tr.attr("taskId");
        var task = self.master.getTask(taskId);
        var changer = $.JST.createFromTemplate({}, "CHANGE_STATUS");
        changer.css("top", tr.position().top + self.element.parent().scrollTop());
        changer.find("[status=" + task.status + "]").addClass("selected");
        changer.find(".taskStatus").click(function () {
            self.master.beginTransaction();
            task.changeStatus($(this).attr("status"));
            self.master.endTransaction();
            el.attr("status", task.status);
            changer.remove();
            el.show();
        });
        el.hide().oneTime(3000, "hideChanger", function () {
            changer.remove();
            $(this).show();
        });
        el.after(changer);
    });
    /*//expand collapse todo to be completed
     taskRow.find(".expcoll").click(function(){
     //expand?
     var el=$(this);
     var taskId=el.closest("[taskId]").attr("taskId");
     var task=self.master.getTask(taskId);
     var descs=task.getDescendant();
     if (el.is(".exp")){
     for (var i=0;i<descs.length;i++)
     descs[i].rowElement.show();
     } else {
     for (var i=0;i<descs.length;i++)
     descs[i].rowElement.hide();
     }
     
     });*/

    //bind row selection
    taskRow.click(function () {
        var row = $(this);
        //var isSel = row.hasClass("rowSelected");
        row.closest("table").find(".rowSelected").removeClass("rowSelected");
        row.addClass("rowSelected");
        //set current task
        self.master.currentTask = self.master.getTask(row.attr("taskId"));
        //move highlighter
        if (self.master.currentTask.ganttElement)
            self.master.gantt.highlightBar.css("top", self.master.currentTask.ganttElement.position().top);
        //if offscreen scroll to element
        var top = row.position().top;
        if (row.position().top > self.element.parent().height()) {
            self.master.gantt.element.parent().scrollTop(row.position().top - self.element.parent().height() + 100);
        }
    });
};
GridEditor.prototype.openFullEditor = function (task, taskRow) {
    var self = this;
    //task editor in popup
    var taskId = taskRow.attr("taskId");
    ////console.debug(task);

    //make task editor
    var taskEditor = $.JST.createFromTemplate({}, "TASK_EDITOR");

    taskEditor.find("#name").val(task.name);
    taskEditor.find("#description").val(task.description);
    taskEditor.find("#code").val(task.code);
    taskEditor.find("#progress").val(task.progress ? parseFloat(task.progress) : 0);
    taskEditor.find("#status").attr("status", task.status);
    if (task.startIsMilestone)
        taskEditor.find("#startIsMilestone").attr("checked", true);
    if (task.endIsMilestone) {
        taskEditor.find("#endIsMilestone").attr("checked", true);
    }
    /*INICIO ampliacion SOFIS - SIGES 12-12-2016*/

//    taskEditor.find("#duration").val(task.duration);
//    taskEditor.find("#start").val(new Date(task.start).format());
//    taskEditor.find("#end").val(new Date(task.end).format());


    if (task.tieneProd || task.estadoTask > 1 || task.esReferencia) {
        taskEditor.find("#endIsMilestone").attr("disabled", "true");
    }

    if (task.endIsMilestone) {
        taskEditor.find("#startPlanDiv").hide();
        taskEditor.find("#startEjecDiv").hide();
        taskEditor.find("#planDuracionDias").hide();
        taskEditor.find("#ejecDuracionDias").hide();
    }



    if (task.estadoTask > 1) {
        /* si está en ejecución o finalizado*/
        taskEditor.find("#start_plan").val(new Date(task.startLineaBase).format());
        taskEditor.find("#end_plan").val(new Date(task.endLineaBase).format());
        taskEditor.find("#duration_plan").val(task.durationLineaBase);
        taskEditor.find("#start_ejec").val(new Date(task.start).format());
        taskEditor.find("#end_ejec").val(new Date(task.end).format());
        taskEditor.find("#duration_ejec").val(task.duration);
        taskEditor.find("#start_plan").attr("disabled", "true");
        taskEditor.find("#end_plan").attr("disabled", "true");
        taskEditor.find("#duration_plan").attr("disabled", "true");
        taskEditor.find("#horasEstimadas").attr("disabled", "true");
        /* les cambio el id y el name por start, end y duration, ya que se buscan estos elementos por esos id */
        taskEditor.find("#start_ejec").attr("name", "start");
        taskEditor.find("#start_ejec").attr("id", "start");
        taskEditor.find("#end_ejec").attr("name", "end");
        taskEditor.find("#end_ejec").attr("id", "end");
        taskEditor.find("#start_ejec_label").attr("for", "start");
        taskEditor.find("#start_ejec_label").attr("id", "start_label");
        taskEditor.find("#end_ejec_label").attr("for", "end");
        taskEditor.find("#end_ejec_label").attr("id", "end_label");
        taskEditor.find("#duration_ejec").attr("name", "duration");
        taskEditor.find("#duration_ejec").attr("id", "duration");
        taskEditor.find("#ejecDuracionDiasSpan").attr("id", "duracionDiasSpan");
        taskEditor.find("#planDuracionDiasSpan").text("Duración: " + taskEditor.find("#duration_plan").val() + " días");
        taskEditor.find("#periodo_inicio").attr("checked", task.inicioProyecto);
        taskEditor.find("#periodo_fin").attr("checked", task.finProyecto);

    } else {
        /* si está en inicio o planificación */
        taskEditor.find("#start_plan").val(new Date(task.start).format());
        taskEditor.find("#end_plan").val(new Date(task.end).format());
        taskEditor.find("#duration_plan").val(task.duration);
        taskEditor.find("#start_ejec").attr("disabled", "true");
        taskEditor.find("#end_ejec").attr("disabled", "true");
        taskEditor.find("#duration_ejec").attr("disabled", "true");
        taskEditor.find("#progress").attr("disabled", "true");

        /* les cambio el id y el name por start, end y duration, ya que se buscan estos elementos por esos id */
        taskEditor.find("#start_plan").attr("name", "start");
        taskEditor.find("#start_plan").attr("id", "start");
        taskEditor.find("#end_plan").attr("name", "end");
        taskEditor.find("#end_plan").attr("id", "end");
        taskEditor.find("#duration_plan").attr("name", "duration");
        taskEditor.find("#duration_plan").attr("id", "duration");
        taskEditor.find("#start_plan_label").attr("for", "start");
        taskEditor.find("#start_plan_label").attr("id", "start_label");
        taskEditor.find("#end_plan_label").attr("for", "end");
        taskEditor.find("#end_plan_label").attr("id", "end_label");
        taskEditor.find("#planDuracionDiasSpan").attr("id", "duracionDiasSpan");
        taskEditor.find("#ejecDuracionDiasSpan").remove();
        taskEditor.find("#horasEstimadas").attr("disabled", "false");
        taskEditor.find("#periodo_inicio").attr("checked", task.inicioProyecto);
        taskEditor.find("#periodo_fin").attr("checked", task.finProyecto);
    }

//    console.log("task.inicioProyecto: "+ task.inicioProyecto);
//    console.log("task.finProyecto: "+ task.finProyecto);

    taskEditor.find("#duracionDiasSpan").text("Duración: " + taskEditor.find("#duration").val() + " días");
    /*FIN ampliacion SOFIS - SIGES 12-12-2016 */

    /*INICIO ampliacion SOFIS - SIGES 1.0*/
    taskEditor.find("#esfuerzo").val(task.esfuerzo);
    taskEditor.find("#horasEstimadas").val(task.horasEstimadas);
    taskEditor.find("#coordinador").val(task.coordinador);
    taskEditor.find("#relevantePMO").attr("checked", task.relevantePMO);
    if (task.tieneProd) {
        taskEditor.find("#tieneProductos").val('Tiene productos asociados.');
    }
    if (task.esReferencia && task.referido) {
        if (task.estadoTask > 1) {
            taskEditor.find("#name").attr("disabled", "true");
            taskEditor.find("#description").attr("disabled", "true");
        }
        taskEditor.find("#coordinador").attr("disabled", "true");
        taskEditor.find("#nombre-referencia").html(task.referido.name);
        taskEditor.find("#numero-entregable").html(task.referido.id);
        taskEditor.find("#codigo-proyecto-refereido").html(task.proyectoReferido.id);
        taskEditor.find("#proyecto-referencia").html(task.proyectoReferido.nombre);
        taskEditor.find("#link-proyecto-referencia").attr("onclick", "navegarAProyecto({id:'" + task.proyectoReferido.id + "'})");
        taskEditor.find("#referido-eliminado").remove();

    } else if (task.esReferencia) {

        taskEditor.find("#div-entregable-referencia").remove();
        taskEditor.find("#div-proyecto-referencia").remove();

    } else {
        taskEditor.find("#referencia").remove();
    }

    if (task.isParent()) {
        taskEditor.find("#start").attr("disabled", "true");
        taskEditor.find("#end").attr("disabled", "true");
    }


    if (self.master.periodoEntregable === false) {
        taskEditor.find("#inicioPeriodoDivRow").remove()
    }


    //make assignments table
    var assigsTable = taskEditor.find("#assigsTable");
    assigsTable.find("[assigId]").remove();
    // loop on already assigned resources
    for (var i = 0; i < task.assigs.length; i++) {
        var assig = task.assigs[i];
        var assigRow = $.JST.createFromTemplate({task: task, assig: assig}, "ASSIGNMENT_ROW");
        assigsTable.append(assigRow);
    }

    if (!self.master.canWrite) {
        taskEditor.find("input,textarea").attr("readOnly", true);
        taskEditor.find("input:checkbox,select").attr("disabled", true);
    } else {
        //bind dateField on dates
        taskEditor.find("#start").click(function () {
            $(this).dateField({
                inputField: $(this),
                callback: function (date) {

                    /* corregido para que se comporte de forma similar al callback de taskEditor.find('#end') */
                    var start = Date.parseString(taskEditor.find("#start").val());
                    var end = Date.parseString(taskEditor.find("#end").val());
                    end.setHours(12, 0, 0, 0);
                    if (end.getTime() < start.getTime()) {
                        var dur = parseInt(taskEditor.find("#duration").val());
                        end = incrementDateByWorkingDays(start.getTime(), dur);
                        taskEditor.find("#end").val(new Date(computeEnd(end)).format());
                    } else {
                        taskEditor.find("#duration").val(recomputeDuration(start.getTime(), end.getTime()));
                    }
                    taskEditor.find("#duracionDiasSpan").text("Duración: " + taskEditor.find("#duration").val() + " días");
                }
            });
        });
        //bind dateField on dates
        taskEditor.find("#end").click(function () {
            $(this).dateField({
                inputField: $(this),
                callback: function (end) {
                    var start = Date.parseString(taskEditor.find("#start").val());
//                    end.setHours(23, 59, 59, 999);
                    end.setHours(12, 0, 0, 0);
                    if (end.getTime() < start.getTime()) {
                        var dur = parseInt(taskEditor.find("#duration").val());
                        start = incrementDateByWorkingDays(end.getTime(), -dur);
                        taskEditor.find("#start").val(new Date(computeStart(start)).format());
                    } else {
                        taskEditor.find("#duration").val(recomputeDuration(start.getTime(), end.getTime()));
                    }
                    taskEditor.find("#duracionDiasSpan").text("Duración: " + taskEditor.find("#duration").val() + " días");
                }
            });
        });
        //bind blur on duration
        taskEditor.find("#duration").change(function () {
            var start = Date.parseString(taskEditor.find("#start").val());
            var el = $(this);
            var dur = parseInt(el.val());
            dur = dur <= 0 ? 1 : dur;
            el.val(dur);
            taskEditor.find("#end").val(new Date(computeEndByDuration(start.getTime(), dur)).format());
            taskEditor.find("#duracionDiasSpan").text("Duración: " + taskEditor.find("#duration").val() + " días");
        });
        //bind add assignment
        taskEditor.find("#addAssig").click(function () {
            var assigsTable = taskEditor.find("#assigsTable");
            var assigRow = $.JST.createFromTemplate({task: task, assig: {id: "tmp_" + new Date().getTime()}}, "ASSIGNMENT_ROW");
            assigsTable.append(assigRow);
        });
        taskEditor.find("#status").click(function () {
            var tskStatusChooser = $(this);
            var changer = $.JST.createFromTemplate({}, "CHANGE_STATUS");
            changer.css("top", tskStatusChooser.position().top);
            changer.find("[status=" + task.status + "]").addClass("selected");
            changer.find(".taskStatus").click(function () {
                tskStatusChooser.attr("status", $(this).attr("status"));
                changer.remove();
                tskStatusChooser.show();
            });
            tskStatusChooser.hide().oneTime(3000, "hideChanger", function () {
                changer.remove();
                $(this).show();
            });
            tskStatusChooser.after(changer);
        });
        //save task
        taskEditor.find("#saveButton").click(function () {
            var task = self.master.getTask(taskId); // get task again because in case of rollback old task is lost

            self.master.beginTransaction();

            var taskInicioPeriodo = self.master.getTaskInicioPeriodo();
            var taskFinPeriodo = self.master.getTaskFinPeriodo();
            var error = false;

            if (taskFinPeriodo != null && taskEditor.find("#periodo_fin").is(":checked") && taskFinPeriodo.id != taskId) {
                console.log("self.master.getTaskFinPeriodoCheck(" + taskId + ") : " + true);
                $('#error_msg').remove();
                $('<div id="error_msg" class="col-sm-12"><span style="color: red;">El entregable "' + taskFinPeriodo.name + '" ya está definido como fin del proyecto</span></div>')
                        .insertBefore("#taskDiv");
                error = true;
            } else {
                console.log("self.master.getTaskFinPeriodoCheck(" + taskId + ") : " + false);
            }

            if (taskInicioPeriodo != null && taskEditor.find("#periodo_inicio").is(":checked") && taskInicioPeriodo.id != taskId) {
                console.log("self.master.getTaskInicioPeriodoCheck(" + taskId + ") : " + true);
                $('#error_msg').remove();
                $('<div id="error_msg" class="col-sm-12"><span style="color: red;">El entregable "' + taskInicioPeriodo.name + '" ya está definido como inicio del proyecto</span></div>')
                        .insertBefore("#taskDiv");
                error = true;
            } else {
                console.log("self.master.getTaskInicioPeriodoCheck(" + taskId + ") : " + false);
            }

            if (task.isParent() && taskEditor.find("#endIsMilestone").is(":checked")) {
                console.log("self.master.getTaskInicioPeriodoCheck(" + taskId + ") : " + true);
                $('#error_msg').remove();
                $('<div id="error_msg" class="col-sm-12"><span style="color: red;">El entregable "' + task.name + '" no puede convertirse en hito, ya que tiene otros entregables que dependen de él.</span></div>')
                        .insertBefore("#taskDiv");
                error = true;
            } else {
                console.log("self.master.getTaskInicioPeriodoCheck(" + taskId + ") : " + false);
            }

            Number.isInteger = Number.isInteger || function (value) {
                return typeof value === "number" &&
                        isFinite(value) &&
                        Math.floor(value) === value;
            };

            var progress = Number(taskEditor.find("#progress").val());
            if (!taskEditor.find("#endIsMilestone").is(":checked") && progress !== null && (isNaN(progress) || !(Number.isInteger(progress)) || progress < 0 || progress > 100)) {
                console.log("self.master.getProgress(" + taskId + ") : " + true);
                $('#error_msg').remove();
                $('<div id="error_msg" class="col-sm-12" style="margin-bottom: 10px;"><span style="color: red;">El progreso del entregable "' + task.name + '" tiene que ser un número entero entre 0 y 100.</span></div>')
                        .insertBefore("#saveButton");
                error = true;
            } else if (taskEditor.find("#endIsMilestone").is(":checked") && progress !== null && (isNaN(progress) || !(Number.isInteger(progress)) || (progress !== 0 && progress !== 100))) {
                console.log("self.master.getProgress(" + taskId + ") : " + true);
                $('#error_msg').remove();
                $('<div id="error_msg" class="col-sm-12" style="margin-bottom: 10px;"><span style="color: red;">El progreso del hito "' + task.name + '" tiene que ser 0 o 100.</span></div>')
                        .insertBefore("#saveButton");
                error = true;
            }

            var esfuerzo = Number(taskEditor.find("#esfuerzo").val());
            if (!(Number.isInteger(esfuerzo)) || esfuerzo < 0) {
                console.log("self.master.getProgress(" + taskId + ") : " + true);
                $('#error_msg').remove();
                $('<div id="error_msg" class="col-sm-12" style="margin-bottom: 10px;"><span style="color: red;">La ponderación del entregable "' + task.name + '" tiene que ser un número entero positivo.</span></div>')
                        .insertBefore("#saveButton");
                error = true;
            }



            if (error === true) {
                return;
            }

            task.name = taskEditor.find("#name").val();
            task.description = taskEditor.find("#description").val();
            task.code = taskEditor.find("#code").val();


            if (isNaN(parseInt(taskEditor.find("#progress").val()))) {
                task.progress = 0;
            } else {
                task.progress = parseInt(taskEditor.find("#progress").val());
            }


            task.duration = parseInt(taskEditor.find("#duration").val());
            task.startIsMilestone = taskEditor.find("#startIsMilestone").is(":checked");
            task.endIsMilestone = taskEditor.find("#endIsMilestone").is(":checked");

            var coord = task.coordinador;
            task.coordinador = parseInt(taskEditor.find("#coordinador").val());

            if (isNaN(parseFloat(taskEditor.find("#esfuerzo").val()))) {
                task.esfuerzo = 0;
            } else {
                task.esfuerzo = parseFloat(taskEditor.find("#esfuerzo").val());
            }





            task.horasEstimadas = taskEditor.find("#horasEstimadas").val();
            var cambiarHijos = taskEditor.find("#cambiarCoordHijos").is(":checked");
            task.relevantePMO = taskEditor.find("#relevantePMO").is(":checked");



            task.inicioProyecto = taskEditor.find("#periodo_inicio").is(":checked");
            task.finProyecto = taskEditor.find("#periodo_fin").is(":checked");


            if (task.endIsMilestone) {
                task.start = task.end;
                taskEditor.find("#start").val(taskEditor.find("#end").val());
            }

            // Solo si es Gerente o adjunto, o coordinador. Y si selecciona un check.
            var isCoord = (coord == self.master.usuarioId);
            console.log('antes PMOEDITOR');
            var isPmoEditor = self.master.isPMOEditor;
            console.log('despues PMOEDITOR');
            if ((self.master.isPM || isCoord || isPmoEditor) && cambiarHijos) {
                var seguir = true;
                var task_Id = task.id;
                task_Id++;
                var taskLevel = task.level;
                var arrTasks = self.master.tasks;
                var encontro = false;
                for (i = 0; i < arrTasks.length && seguir; i++) {
                    try {
                        var t = arrTasks[i];
                        if (encontro &&
                                typeof t !== 'undefined' && t !== null && t.level > taskLevel) {
                            if (!t.esReferencia) {
                                t.coordinador = task.coordinador;
                            }
                        } else if (encontro) {
                            seguir = false;
                        }

                        if (encontro || t.id === task.id) {
                            encontro = true;
                        }
                    } catch (ex) {
                        console.log("Error en linea 721 GDE");
                    }

                }
            }

            //set assignments
            taskEditor.find("tr[assigId]").each(function () {
                var trAss = $(this);
                var assId = trAss.attr("assigId");
                var resId = trAss.find("[name=resourceId]").val();
                var roleId = trAss.find("[name=roleId]").val();
                var effort = millisFromString(trAss.find("[name=effort]").val());
                //check if an existing assig has been deleted and re-created with the same values
                var found = false;
                for (var i = 0; i < task.assigs.length; i++) {
                    try {
                        var ass = task.assigs[i];
                        if (assId == ass.id) {
                            ass.effort = effort;
                            ass.roleId = roleId;
                            ass.resourceId = resId;
                            ass.touched = true;
                            found = true;
                            break;
                        } else if (roleId == ass.roleId && resId == ass.resourceId) {
                            ass.effort = effort;
                            ass.touched = true;
                            found = true;
                            break;
                        }
                    } catch (ex) {
                        console.log("Error en linea 753 GDE");
                    }
                }

                if (!found) { //insert
                    var ass = task.createAssignment("tmp_" + new Date().getTime(), resId, roleId, effort);
                    ass.touched = true;
                }

            });
            //remove untouched assigs
            task.assigs = task.assigs.filter(function (ass) {
                var ret = ass.touched;
                delete ass.touched;
                return ret;
            });
//            var diamilis = 86400000;


            var dateEditorStart = Date.parseString(taskEditor.find("#start").val());
            dateEditorStart.setHours(12);
            dateEditorStart.setMinutes(0);
            dateEditorStart.setSeconds(0);
//            dateEditorStart.setMilliseconds(0);

            var dateStart = new Date();
            dateStart.setTime(task.start);
            dateStart.setHours(12);
            dateStart.setMinutes(0);
            dateStart.setSeconds(0);
//            dateStart.setMilliseconds(0);


            var difStart = (task.start - Date.parseString(taskEditor.find("#start").val()).getTime());
            if (difStart < 0) {
                difStart = difStart * -1;
            }

            var dateEditorEnd = Date.parseString(taskEditor.find("#end").val());
            dateEditorEnd.setHours(12);
            dateEditorEnd.setMinutes(0);
            dateEditorEnd.setSeconds(0);
//            dateEditorEnd.setMilliseconds(0);

            var dateEnd = new Date();
            dateEnd.setTime(task.end);
            dateEnd.setHours(12);
            dateEnd.setMinutes(0);
            dateEnd.setSeconds(0);
//            dateEnd.setMilliseconds(0);

            var difEnd = (task.end - Date.parseString(taskEditor.find("#end").val()).getTime());
            if (difEnd < 0) {
                difEnd = difEnd * -1;
            }

            if (dateStart.getFullYear() !== dateEditorStart.getFullYear()
                    || dateStart.getMonth() !== dateEditorStart.getMonth()
                    || dateStart.getDate() !== dateEditorStart.getDate()
                    || dateEnd.getFullYear() !== dateEditorEnd.getFullYear()
                    || dateEnd.getMonth() !== dateEditorEnd.getMonth()
                    || dateEnd.getDate() !== dateEditorEnd.getDate()
                    ) {

//                //console.log("Cambiar Periodo.");
                //change dates - SOFIS CAMBIO se quita que le SUMe 24 HORAS
                //task.setPeriod(Date.parseString(taskEditor.find("#start").val()).getTime(), Date.parseString(taskEditor.find("#end").val()).getTime() + (3600000 * 24));
                task.setPeriod(Date.parseString(taskEditor.find("#start").val()).getTime(), Date.parseString(taskEditor.find("#end").val()).getTime());
//                task.setPeriod(dateEditorStart.getTime(), dateEditorEnd.getTime());
            } else {
                task.duration = recomputeDuration(Date.parseString(taskEditor.find("#start").val()).getTime(), Date.parseString(taskEditor.find("#end").val()).getTime());
            }

            //change status



            task.changeStatus(taskEditor.find("#status").attr("status"));
            if (self.master.endTransaction()) {
                $("#__blackpopup__").trigger("close");
            }
        });
    }

    var ndo = createBlackPage(800, 400).append(taskEditor);
    // Carga el combo de Coordinadores
    selectCoord = document.getElementById("coordinador");
    var coord = this.master.coordinadores;

    //console.log("imprimo coord a ver que sale");
    //console.log(coord);

    for (var i = 0; i < coord.length; i++) {
        var arrSplit = coord[i].split(":");
        try {
            var opt = document.createElement('option');
            opt.value = arrSplit[0];
            opt.text = arrSplit[1];
            if (task.coordinador == arrSplit[0]) {
                opt.setAttribute('selected', true);
            }
            selectCoord.add(opt);
        } catch (err) {
//	    alert("Error:" + err);
            $('#ganttDialogUtilsMsgDiv').text("Error:" + err);
            ganttDialogUtils.show();
        }
    }

    if (task.tieneProd && task.progress % 10 !== 0) {
        var opt = document.createElement('option');
        opt.value = task.progress;
        opt.text = task.progress;
        opt.setAttribute('selected', true);
        //selectProgress.add(opt);
    }


    if (task.parent || this.master.estado === 5) {
        document.getElementById("esfuerzo").setAttribute('disabled', 'true');
    }

    if (task.parent || this.master.estado === 5 || task.esReferencia) {

        document.getElementById("progress").setAttribute('disabled', 'true');
    }

    if (this.master.estado === 4 || this.master.estado === 5) {
        document.getElementById("name").setAttribute('disabled', 'true');
        document.getElementById("esfuerzo").setAttribute('disabled', 'true');
    }


    if (task.tieneProd) {
        document.getElementById("progress").setAttribute('disabled', 'true');
    }

    var isCoord = (task.coordinador === this.master.usuarioId);
    var isPM = this.master.isPM;
    var isPmoEditor = this.master.isPMOEditor;
    var disable = !(isPM || isCoord || isPmoEditor);

    if (disable || this.master.estado === 5) {
        document.getElementById("name").setAttribute('disabled', 'true');
        document.getElementById("esfuerzo").setAttribute('disabled', 'true');
    }

    if (disable || this.master.estado === 5 || task.esReferencia) {
//        document.getElementById("description").setAttribute('disabled', 'true');
        document.getElementById("coordinador").setAttribute('disabled', 'true');
        document.getElementById("tieneProductos").setAttribute('disabled', 'true');
        document.getElementById("start").setAttribute('disabled', 'true');
        document.getElementById("progress").setAttribute('disabled', 'true');
        document.getElementById("end").setAttribute('disabled', 'true');
        document.getElementById("horasEstimadas").setAttribute('disabled', 'true');
        document.getElementById("cambiarCoordHijos").setAttribute('disabled', 'true');
    }

    var isPMO = (this.master.isPMO);
    if (!isPMO || this.master.estado === 5 || task.esReferencia) {
        document.getElementById("relevantePMO").setAttribute('disabled', 'true');
    } else {
        document.getElementById("relevantePMO").removeAttribute('disabled');
        document.getElementById("relevantePMO").removeAttribute('readonly');
    }

    if (isPmoEditor) {
        document.getElementById("description").removeAttribute('readonly');
    }

    if (!isPM || this.master.estado === 5) {
        document.getElementById("addAboveBtn").setAttribute('disabled', 'true');
        document.getElementById("addBelowBtn").setAttribute('disabled', 'true');
        document.getElementById("indentBtn").setAttribute('disabled', 'true');
        document.getElementById("outdentBtn").setAttribute('disabled', 'true');
        document.getElementById("moveUpBtn").setAttribute('disabled', 'true');
        document.getElementById("moveDownBtn").setAttribute('disabled', 'true');
        document.getElementById("deleteBtn").setAttribute('disabled', 'true');
    }

    if ((disable && !isPMO) || this.master.estado == 5) {
        document.getElementById("saveButton").setAttribute('disabled', 'true');
    }
};

function closeEditorDiv() {
    $("#__blackpopup__").trigger("close");
}