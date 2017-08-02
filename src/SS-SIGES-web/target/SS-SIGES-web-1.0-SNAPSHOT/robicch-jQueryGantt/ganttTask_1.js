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

/**
 * A method to instantiate valid task models from
 * raw data.
 */
function TaskFactory() {

    /**
     * Build a new Task
     */
    this.build = function (id, name, code, level, start, duration, taskPk, coordId, startLineaBase, durationLineaBase) {
        // Set at beginning of day
        var adjusted_start = computeStart(start);
        var calculated_end = computeEndByDuration(adjusted_start, duration);

        var adjusted_startLineaBase = 0;
        var calculated_endLineaBase = 0;
        if (durationLineaBase >= 0) {
            if (startLineaBase > 0) {
                adjusted_startLineaBase = computeStart(startLineaBase);
                calculated_endLineaBase = computeEndByDuration(startLineaBase, durationLineaBase);
            }
        }

        var t = new Task(id, name, code, level, adjusted_start, calculated_end, duration, taskPk, coordId);

        t.endLineaBase = calculated_endLineaBase;
        t.startLineaBase = adjusted_startLineaBase;

        return t;
    };
}

function Task(id, name, code, level, start, end, duration, taskPk, coordId) {
    
    console.debug("Task.new  " + name);
    this.id = id;
    this.name = name;
    this.code = code;
    this.level = level;
    this.status = "STATUS_UNDEFINED";

    this.start = start;
    this.duration = duration;
    this.end = end;

    this.startIsMilestone = false;
    this.endIsMilestone = false;

    this.collapsed = false;

    this.rowElement; //row editor html element
    this.ganttElement; //gantt html element
    this.master;

    this.assigs = [];

    /*INICIO ampliacion SOFIS - SIGES 1.0*/
    this.coordinador = (coordId != null && coordId != undefined ? coordId : 0);
    this.esfuerzo = 0;
    this.horasEstimadas;
    this.startLineaBase = 0;
    this.durationLineaBase = 0;
    this.endLineaBase = 0;
    if (taskPk != null) {
        this.taskPk = taskPk;
    }
    this.parent = false;
    this.canDelete = true;
    this.tieneProd = false;
    this.relevantePMO = false;
    /*FIN ampliacion SOFIS - SIGES 1.0*/

    /*INICIO ampliacion SOFIS - SIGES 12-12-2016 */
    this.estadoTask = 0; /* 0 = inicio, 1 = planificación, 2 = ejecución, 3 = finalizado  */
    /*FIN ampliacion SOFIS - SIGES 12-12-2016 */

    /*SOFIS - SIGES 06-04-2017 */
    this.inicioProyecto = false;
    this.finProyecto = false;
    this.setPeriodo = false;
    /*SOFIS - SIGES 06-04-2017 */

}


Task.prototype.clone = function () {
    var ret = {};
    for (var key in this) {
        if (typeof (this[key]) != "function") {
            ret[key] = this[key];
        }
    }
    return ret;
};

Task.prototype.getAssigsString = function () {
    var ret = "";
    for (var i = 0; i < this.assigs.length; i++) {
        var ass = this.assigs[i];
        var res = this.master.getResource(ass.resourceId);
        if (res) {
            ret = ret + (ret == "" ? "" : ", ") + res.name;
        }
    }
    return ret;
};

Task.prototype.createAssignment = function (id, resourceId, roleId, effort) {
    var assig = new Assignment(id, resourceId, roleId, effort);
    this.assigs.push(assig);
    return assig;
};


//<%---------- SET PERIOD
//Se invoca cuando se agranda un entregable o cuando se mueve.
//TODO considerar caso de hito con superior que se hace.
// ---------------------- --%>
Task.prototype.setPeriod = function (start, end) {
    if (start instanceof Date) {
        start = start.getTime();
    }

    if (end instanceof Date) {
        end = end.getTime();
    }

    var originalPeriod = {
        start: this.start,
        end: this.end,
        duration: this.duration
    };

    var wantedStartMillis = start;

    //cannot start after end
    if (start > end) {
        start = end;
    }

    //set a legal start
    start = computeStart(start);

    //if depends -> start is set to max end + lag of superior
    var sups = this.getSuperiors();
    if (sups && sups.length > 0) {
        var supEnd = 0;
        for (var i = 0; i < sups.length; i++) {
            var link = sups[i];
            supEnd = Math.max(supEnd, incrementDateByWorkingDays(link.from.end, link.lag));
        }

        //if changed by depends move it

        //sofis el end del padre le sumo 1 pero puede
        //existir un caso que el end del padre ya se cumpla que sea igual
        supEnd = computeStart(supEnd);
        if (supEnd != start) {
            supEnd = supEnd + (3600000 * 24 * 1);
        }


        if (supEnd != start) {
            return this.moveTo(supEnd + 1, false);
        }
    }

    var somethingChanged = false;

    //move date to closest day
    var date = new Date(start);

    if (this.start != start || this.start != wantedStartMillis) {
        this.start = start;
        somethingChanged = true;
    }

    //set end
    var wantedEndMillis = end;

    end = computeEnd(end);
    //SERGIO: 04/11/2015: si tiene hijos el end no puede ser mayor al maximo end de los hijos
    //BRUNO : 30/03/2017: si tiene hijos el start no puede ser menor al minimo start de los hijos
    var children = this.getChildren();
    var maxEnd = 0;
    var minStart = 0;
    for (var i = 0; i < children.length; i++) {
        //BRUNO : 27/04/2017: los hitos no aplican a temas de dependencias
//        if (!children[i].endIsMilestone) {
            maxEnd = Math.max(maxEnd, children[i].end);
            minStart = Math.min(minStart, children[i].start);
//        }
    }
    if (maxEnd > 0) {
        end = maxEnd;
    }
    if (minStart > 0) {
        start = minStart;
    }

    if (this.end != end || this.end != wantedEndMillis) {
        this.end = end;
        somethingChanged = true;
    }
    if (this.start != start || this.start != wantedStartMillis) {
        this.start = start;
        somethingChanged = true;
    }

    //BRUNO 19/04/2017: si es un hito tiene que setear fecha de inicio igual a la de fin.
    //tampoco tenemos que dejar mover si tiene superiores
    if (this.endIsMilestone === true) {
	
	//si es un hito con superior y algo cambio no se deja mover
	//01-06-2017
	var supsHito = this.getSuperiors();
	if ( (supsHito && supsHito.length > 0)  && (originalPeriod.end !== this.end || originalPeriod.start !== this.start)) {
	    this.master.setErrorOnTransaction(GanttMaster.messages["START_IS_MILESTONE_DEPENDENCIA"], this);
	    return false;
	}
	
        if (originalPeriod.end !== this.end && originalPeriod.start === this.start) {
            this.start = this.end;
            somethingChanged = true;
        } else if (originalPeriod.start !== this.start && originalPeriod.end === this.end) {
            this.end = this.start;
            somethingChanged = true;
        }
    }

    this.duration = recomputeDuration(this.start, this.end);

    //profilerSetPer.stop();

    //nothing changed exit
    if (!somethingChanged)
        return true;

    //external dependencies: exit with error
    if (this.hasExternalDep) {
        this.master.setErrorOnTransaction(GanttMaster.messages["TASK_HAS_EXTERNAL_DEPS"] + "\n" + this.name, this);
        return false;
    }

    var todoOk = true;

    //I'm restricting
    var deltaPeriod = originalPeriod.duration - this.duration;
    var restricting = deltaPeriod > 0;
    var restrictingStart = restricting && (originalPeriod.start < this.start);
    var restrictingEnd = restricting && (originalPeriod.end > this.end);

    if (restricting) {
        //loops children to get boundaries
        var children = this.getChildren();
        var bs = Infinity;
        var be = 0;
        for (var i = 0; i < children.length; i++) {

            ch = children[i];
            if (restrictingEnd) {
                be = Math.max(be, ch.end);
            } else {
                bs = Math.min(bs, ch.start);
            }
        }

        if (restrictingEnd) {
            this.end = Math.max(be, this.end);
        } else {
            this.start = Math.min(bs, this.start);
        }

        this.duration = recomputeDuration(this.start, this.end);

        //SERGIO: actualizar los padres
        todoOk = updateTree(this);


    } else {

        //check global boundaries
        if (this.start < this.master.minEditableDate || this.end > this.master.maxEditableDate) {
            this.master.setErrorOnTransaction(GanttMaster.messages["CHANGE_OUT_OF_SCOPE"], this);
            todoOk = false;
        }

        if (todoOk && !updateTree(this)) {
            todoOk = false;
        }
    }

    if (todoOk) {
        //and now propagate to inferiors
        var infs = this.getInferiors();
        if (infs && infs.length > 0) {
            for (var i = 0; i < infs.length; i++) {
                var link = infs[i];
                //sofis change
                todoOk = link.to.moveTo(end, false); //this is not the right date but moveTo checks start
                if (!todoOk)
                    break;
            }
        }
    }

    return todoOk;
};


//<%---------- MOVE TO
//Este metodo se llama recursivo cada ves que movemos una fecha o que movemos 
//desde el gant una tarea, para la tarea incial que movemos se llama para sus
//inferiores a este metodo para acomodarlos
//--%>
Task.prototype.moveTo = function (start, ignoreMilestones) {
    if (start instanceof Date) {
        start = start.getTime();
    }

    var originalPeriod = {
        start: this.start,
        end: this.end
    };

    var wantedStartMillis = start;

    //set a legal start
    start = computeStart(start);

    //if start is milestone cannot be move
    if (!ignoreMilestones && this.startIsMilestone && start != this.start) {
        //notify error
        this.master.setErrorOnTransaction(GanttMaster.messages["START_IS_MILESTONE"], this);
        return false;
    } else if (this.hasExternalDep) {
        //notify error
        this.master.setErrorOnTransaction(GanttMaster.messages["TASK_HAS_EXTERNAL_DEPS"], this);
        return false;
    }

    //if depends start is set to max end + lag of superior
    var sups = this.getSuperiors();
    if (sups && sups.length > 0) {
        var supEnd = 0;
        for (var i = 0; i < sups.length; i++) {
            var link = sups[i];
            supEnd = Math.max(supEnd, incrementDateByWorkingDays(link.from.end, link.lag));
        }
	//Como tienen superior se busca el fin del superior y se 
	//le suma un dia para que comience un dia despues
	//que el superior, es decir recalcula el inicio.
        start = supEnd + (1000 * 60 * 60 * 24); //Sumar 1 dia.
    }

    //set a legal start
    start = computeStart(start);

    //sofis: 03/11/2015: restar 1 a la duracion porque el dia actual cuenta como dia de ejecucion
    //sofis: 04/11/2015: esto ya no cuenta porque se puso en el metodo calculateEndByDuration
    var localDuration = this.duration;
    
    //  if (localDuration > 0) {
    //    localDuration = localDuration - 1;
    //  }
    var end = computeEndByDuration(start, localDuration);

    if (this.start != start || this.start != wantedStartMillis) {
        //si es un milestone (un hito)
        if (!ignoreMilestones && this.endIsMilestone) {
            //si es un hito entonces el end es el start y la duracion es 1
	    //01-06-2017
	    end = start;
            //this.duration = recomputeDuration(start, end);
	    //01-06-2017
	    this.duration = 1;
        }
        this.start = start;
        this.end = end;
        //profiler.stop();

        //check global boundaries
        if (this.start < this.master.minEditableDate || this.end > this.master.maxEditableDate) {
            this.master.setErrorOnTransaction(GanttMaster.messages["CHANGE_OUT_OF_SCOPE"], this);
            return false;
        }


        var panDelta = originalPeriod.start - this.start;
        //loops children to shift them
        var children = this.getChildren();
        for (var i = 0; i < children.length; i++) {
            ch = children[i];
            if (!ch.moveTo(ch.start - panDelta, false)) {
                return false;
            }
        }

        if (!updateTree(this)) {
            return false;
        }

        //and now propagate to inferiors
        var infs = this.getInferiors();
        if (infs && infs.length > 0) {
            for (var i = 0; i < infs.length; i++) {
                var link = infs[i];

                //this is not the right date but moveTo checks start
		//para cada dependiente lo mueve segun el nuevo fin de 
		// esta tarea.
                if (!link.to.moveTo(end, false)) {
                    return false;
                }
            }
        }

    }

    return true;
};


function updateTree(task) {
    var error;

    //try to enlarge parent
    var p = task.getParent();

    //no parent:exit
    if (!p)
        return true;

    var newStart = p.start;
    var newEnd = p.end;

    if (p.start > task.start) {
        if (p.startIsMilestone) {
//           BRUNO 24-04-2016 Elimina dependencia de las fechas con los hitos
//            task.master.setErrorOnTransaction(GanttMaster.messages["START_IS_MILESTONE"] + "\n" + p.name, task);
//            return false;
        } else if (p.depends) {
            //Si el padre depende de otra tarea no se puede mover
            task.master.setErrorOnTransaction(GanttMaster.messages["TASK_HAS_CONSTRAINTS"] + " " + p.name, task);
            return false;
        }

        newStart = task.start;
    } else {

        //Hay que ver entre todos los hermanos de la tarea actual cual tiene comienzo menor
        if (p.depends) {
            //Si el padre depende de otra tarea no se puede mover
            task.master.setErrorOnTransaction(GanttMaster.messages["TASK_HAS_CONSTRAINTS"] + " " + p.name, task);
            return false;
        }
        var brothers = p.getChildren();
        var maxStart = task.start;
        for (var i = 0; i < brothers.length; i++) {
            if (brothers[i].start < maxStart) {
                maxStart = brothers[i].start;
            }
        }
        newStart = maxStart;


    }

    if (p.end < task.end) {
//           BRUNO 24-04-2016 Elimina dependencia de las fechas con los hitos
//        if (p.endIsMilestone) {
//            task.master.setErrorOnTransaction(GanttMaster.messages["END_IS_MILESTONE"] + "\n" + p.name, task);
//            return false;
//        }

        newEnd = task.end;
    } else {
        //SERGIO:
        //hay que buscar entre todos los hermanos el de mayor duracion
        var brothers = p.getChildren();
        var maxEnd = 0;
        for (var i = 0; i < brothers.length; i++) {
            if (brothers[i].end > maxEnd) {
                maxEnd = brothers[i].end;
            }
        }
        newEnd = maxEnd;

    }

    //propagate updates if needed
    if (newStart != p.start || newEnd != p.end) {
        //has external deps ?
        if (p.hasExternalDep) {
            task.master.setErrorOnTransaction(GanttMaster.messages["TASK_HAS_EXTERNAL_DEPS"] + "\n" + p.name, task);
            return false;
        }

        var res = p.setPeriod(newStart, newEnd);
        return res;
    }


    return true;
}

//<%---------- CHANGE STATUS ---------------------- --%>
Task.prototype.changeStatus = function (newStatus) {
    //compute descendant for identify a cone where status changes propagate
    var cone = this.getDescendant();

    function propagateStatus(task, newStatus, manuallyChanged, propagateFromParent, propagateFromChildren) {
        /* var oldStatus = task.status;
         
         //no changes exit
         if(newStatus == oldStatus){
         return true;
         }
         ////console.debug("propagateStatus: "+task.name + " from " + task.status + " to " + newStatus + " " + (manuallyChanged?" a manella":"")+(propagateFromParent?" da parent":"")+(propagateFromChildren?" da children":""));
         
         var todoOk = true;
         task.status = newStatus;
         
         //xxxx -> STATUS_DONE            may activate dependent tasks, both suspended and undefined. Will set to done all descendants.
         //STATUS_FAILED -> STATUS_DONE          do nothing if not forced by hand
         if (newStatus == "STATUS_DONE") {
         
         if ((manuallyChanged || oldStatus != "STATUS_FAILED")) { //cannot change for cascade when failed
         
         //can be closed only if superiors are already done
         var sups = task.getSuperiors();
         for (var i=0;i<sups.length;i++) {
         if (cone.indexOf(sups[i].from) < 0) {
         if (sups[i].from.status != "STATUS_DONE") {
         if (manuallyChanged || propagateFromParent)
         task.master.setErrorOnTransaction(GanttMaster.messages["GANTT_ERROR_DEPENDS_ON_OPEN_TASK"] + "\n" + sups[i].from.name + " -> " + task.name);
         todoOk = false;
         break;
         }
         }
         }
         
         if (todoOk) {
         //todo set progress to 100% if set on config
         
         var chds = task.getChildren();
         //set children as done
         for (var i=0;i<chds.length;i++)
         propagateStatus(chds[i], "STATUS_DONE", false,true,false);
         
         //set inferiors as active if outside the cone
         propagateToInferiors(cone, task.getInferiors(), "STATUS_ACTIVE");
         }
         } else {
         todoOk = false;
         }
         
         
         //  STATUS_UNDEFINED -> STATUS_ACTIVE       all children become active, if they have no dependencies.
         //  STATUS_SUSPENDED -> STATUS_ACTIVE       sets to active all children and their descendants that have no inhibiting dependencies.
         //  STATUS_DONE -> STATUS_ACTIVE            all those that have dependencies must be set to suspended.
         //  STATUS_FAILED -> STATUS_ACTIVE          nothing happens: child statuses must be reset by hand.
         } else if (newStatus == "STATUS_ACTIVE") {
         
         if ((manuallyChanged || oldStatus != "STATUS_FAILED")) { //cannot change for cascade when failed
         
         //activate parent if closed
         var par=task.getParent();
         if (par && par.status != "STATUS_ACTIVE") {
         todoOk=propagateStatus(par,"STATUS_ACTIVE",false,false,true);
         }
         
         if(todoOk){
         //can be active only if superiors are already done
         var sups = task.getSuperiors();
         for (var i=0;i<sups.length;i++) {
         if (sups[i].from.status != "STATUS_DONE") {
         if (manuallyChanged || propagateFromChildren)
         task.master.setErrorOnTransaction(GanttMaster.messages["GANTT_ERROR_DEPENDS_ON_OPEN_TASK"] + "\n" + sups[i].from.name + " -> " + task.name);
         todoOk = false;
         break;
         }
         }
         }
         
         if (todoOk) {
         var chds = task.getChildren();
         if (oldStatus == "STATUS_UNDEFINED" || oldStatus == "STATUS_SUSPENDED") {
         //set children as active
         for (var i=0;i<chds.length;i++)
         if (chds[i].status != "STATUS_DONE" )
         propagateStatus(chds[i], "STATUS_ACTIVE", false,true,false);
         }
         
         //set inferiors as suspended
         var infs = task.getInferiors();
         for (var i=0;i<infs.length;i++)
         propagateStatus(infs[i].to, "STATUS_SUSPENDED", false,false,false);
         }
         } else {
         todoOk = false;
         }
         
         // xxxx -> STATUS_SUSPENDED       all active children and their active descendants become suspended. when not failed or forced
         // xxxx -> STATUS_UNDEFINED       all active children and their active descendants become suspended. when not failed or forced
         } else if (newStatus == "STATUS_SUSPENDED" || newStatus == "STATUS_UNDEFINED") {
         if (manuallyChanged || oldStatus != "STATUS_FAILED") { //cannot change for cascade when failed
         
         //suspend parent if not active
         var par=task.getParent();
         if (par && par.status != "STATUS_ACTIVE") {
         todoOk=propagateStatus(par,newStatus,false,false,true);
         }
         
         
         var chds = task.getChildren();
         //set children as active
         for (var i=0;i<chds.length;i++){
         if (chds[i].status != "STATUS_DONE")
         propagateStatus(chds[i], newStatus, false,true,false);
         }
         
         //set inferiors as STATUS_SUSPENDED or STATUS_UNDEFINED
         propagateToInferiors(cone, task.getInferiors(), newStatus);
         } else {
         todoOk = false;
         }
         
         // xxxx -> STATUS_FAILED children and dependent failed
         } else if (newStatus == "STATUS_FAILED") {
         var chds = task.getChildren();
         //set children as failed
         for (var i=0;i<chds.length;i++)
         propagateStatus(chds[i], "STATUS_FAILED", false,true,false);
         
         //set inferiors as active
         //set children as done
         propagateToInferiors(cone, task.getInferiors(), "STATUS_FAILED");
         }
         if (!todoOk){
         task.status = oldStatus;
         ////console.debug("status rolled back: "+task.name + " to " + oldStatus);
         }
         
         return todoOk;*/

        return true;
    }

    /**
     * A helper method to traverse an array of 'inferior' tasks
     * and signal a status change.
     */
    function propagateToInferiors(cone, infs, status) {
        for (var i = 0; i < infs.length; i++) {
            if (cone.indexOf(infs[i].to) < 0) {
                propagateStatus(infs[i].to, status, false, false, false);
            }
        }
    }

    var todoOk = true;
    var oldStatus = this.status;

    todoOk = propagateStatus(this, newStatus, true, false, false);

    if (!todoOk)
        this.status = oldStatus;

    return todoOk;
};

Task.prototype.synchronizeStatus = function () {
    var oldS = this.status;
    this.status = "";
    return this.changeStatus(oldS);
};

Task.prototype.isLocallyBlockedByDependencies = function () {
    var sups = this.getSuperiors();
    var blocked = false;
    for (var i = 0; i < sups.length; i++) {
        if (sups[i].from.status != "STATUS_DONE") {
            blocked = true;
            break;
        }
    }
    return blocked;
};

//<%---------- TASK STRUCTURE ---------------------- --%>
Task.prototype.getRow = function () {
    ret = -1;
    if (this.master)
        ret = this.master.tasks.indexOf(this);
    return ret;
};


Task.prototype.getParents = function () {
    var ret;
    if (this.master) {
        var topLevel = this.level;
        var pos = this.getRow();
        ret = [];
        for (var i = pos; i >= 0; i--) {
            var par = this.master.tasks[i];
            if (topLevel > par.level) {
                topLevel = par.level;
                ret.push(par);
            }
        }
    }
    return ret;
};

Task.prototype.getParent = function () {
    var ret;
    if (this.master) {
        for (var i = this.getRow(); i >= 0; i--) {
            var par = this.master.tasks[i];
            if (this.level > par.level) {
                ret = par;
                break;
            }
        }
    }
    return ret;
};

Task.prototype.isParent = function () {
    var ret = false;
    if (this.master) {
        var pos = this.getRow();
        if (pos < this.master.tasks.length - 1)
            ret = this.master.tasks[pos + 1].level > this.level;
    }
    return ret;
};

Task.prototype.getChildren = function () {
    var ret = [];
    if (this.master) {
        var pos = this.getRow();
        for (var i = pos + 1; i < this.master.tasks.length; i++) {
            var ch = this.master.tasks[i];
            if (ch.level == this.level + 1)
                ret.push(ch);
            else if (ch.level <= this.level) // exit loop if parent or brother
                break;
        }
    }
    return ret;
};


Task.prototype.getDescendant = function () {
    var ret = [];
    if (this.master) {
        var pos = this.getRow();
        for (var i = pos + 1; i < this.master.tasks.length; i++) {
            var ch = this.master.tasks[i];
            if (ch.level > this.level)
                ret.push(ch);
            else
                break;
        }
    }
    return ret;
};


Task.prototype.getSuperiors = function () {
    var ret = [];
    var task = this;
    if (this.master) {
        ret = this.master.links.filter(function (link) {
            return link.to == task;
        });
    }
    return ret;
};


Task.prototype.getInferiors = function () {
    var ret = [];
    var task = this;
    if (this.master) {
        ret = this.master.links.filter(function (link) {
            return link.from == task;
        });
    }
    return ret;
};


Task.prototype.deleteTask = function () {
    //delete both dom elements
    this.rowElement.remove();
    this.ganttElement.remove();

    //remove children
    var chd = this.getChildren();
    for (var i = 0; i < chd.length; i++) {
        //add removed child in list
        if (!chd[i].isNew())
            this.master.deletedTaskIds.push(chd[i].id);
        chd[i].deleteTask();
    }

    if (!this.isNew())
        this.master.deletedTaskIds.push(this.id);


    //remove from in-memory collection
    this.master.tasks.splice(this.getRow(), 1);

    //remove from links
    var task = this;
    this.master.links = this.master.links.filter(function (link) {
        return link.from != task && link.to != task;
    });
};


Task.prototype.isNew = function () {
    return (this.id + "").indexOf("tmp_") == 0;
};

//<%------------------------------------------  INDENT/OUTDENT --------------------------------%>
Task.prototype.indent = function () {
    
    
                    
    //a row above must exist
    var row = this.getRow();

    //no row no party
    if (row <= 0)
        return false;

    var ret = false;
    var taskAbove = this.master.tasks[row - 1];
    var newLev = this.level + 1;
    if (newLev <= taskAbove.level + 1) {
        ret = true;
        //trick to get parents after indent
        this.level++;
        var futureParents = this.getParents();
        this.level--;
        var oldLevel = this.level;
        for (var i = row; i < this.master.tasks.length; i++) {
            var desc = this.master.tasks[i];
            if (desc.level > oldLevel || desc == this) {
                desc.level++;
                //remove links from descendant to my parents
                this.master.links = this.master.links.filter(function (link) {
                    var linkToParent = false;
                    if (link.to == desc)
                        linkToParent = futureParents.indexOf(link.from) >= 0;
                    else if (link.from == desc)
                        linkToParent = futureParents.indexOf(link.to) >= 0;
                    return !linkToParent;
                });
            } else
                break;
        }

        var parent = this.getParent();
        if (parent && this.start < parent.start && parent.depends && !this.depends) {
            var new_end = computeEndByDuration(parent.start, this.duration);
            this.master.changeTaskDates(this, parent.start, new_end);
        }
        //recompute depends string
        this.master.updateDependsStrings();
        //enlarge parent using a fake set period
        //this.setPeriod(this.start + 1, this.end + 1);
        this.setPeriod(this.start + 1, this.end);

        //force status check
        this.synchronizeStatus();
        
    }
    
    //BRUNO 28-04-16: verificar que el padre no sea hito.
    if(this.getParent()){
        if(this.getParent().endIsMilestone){
            this.master.setErrorOnTransaction(GanttMaster.messages["GANTT_CANNOT_ADD_HITOS_CHILDS"], this.getParent());
            return false;
        }
    }
    
    
    return ret;
};


Task.prototype.outdent = function () {
    //a level must be >1 -> cannot escape from root
    if (this.level <= 1)
        return false;

    var ret = false;
    var oldLevel = this.level;


    ret = true;
    var row = this.getRow();
    for (var i = row; i < this.master.tasks.length; i++) {
        var desc = this.master.tasks[i];
        if (desc.level > oldLevel || desc == this) {
            desc.level--;
        } else
            break;
    }

    var task = this;
    var chds = this.getChildren();
    
    if(this.endIsMilestone && chds != null && chds.length > 0){
        this.master.setErrorOnTransaction(GanttMaster.messages["GANTT_CANNOT_ADD_HITOS_CHILDS"], this.getParent());
        return false;
    }
    
    //remove links from me to my new children
    this.master.links = this.master.links.filter(function (link) {
        var linkExist = (link.to == task && chds.indexOf(link.from) >= 0 || link.from == task && chds.indexOf(link.to) >= 0);
        return !linkExist;
    });


    //enlarge me if inherited children are larger
    for (var i = 0; i < chds.length; i++) {
        //remove links from me to my new children
        //chds[i].setPeriod(chds[i].start + 1, chds[i].end + 1);
        chds[i].setPeriod(chds[i].start + 1, chds[i].end);
    }

    //recompute depends string
    this.master.updateDependsStrings();

    //enlarge parent using a fake set period
    //this.setPeriod(this.start + 1, this.end + 1);
    this.setPeriod(this.start + 1, this.end);

    //force status check
    this.synchronizeStatus();
    
    
    
    return ret;
};


//<%------------------------------------------  MOVE UP / MOVE DOWN --------------------------------%>
Task.prototype.moveUp = function () {
    var ret = false;

    //a row above must exist
    var row = this.getRow();

    //no row no party
    if (row <= 0)
        return false;

    //find new row
    var newRow;
    for (newRow = row - 1; newRow >= 0; newRow--) {
        if (this.master.tasks[newRow].level <= this.level)
            break;
    }

    //is a parent or a brother
    if (this.master.tasks[newRow].level == this.level) {
        ret = true;
        //compute descendant
        var descNumber = 0;
        for (var i = row + 1; i < this.master.tasks.length; i++) {
            var desc = this.master.tasks[i];
            if (desc.level > this.level) {
                descNumber++;
            } else {
                break;
            }
        }
        //move in memory
        var blockToMove = this.master.tasks.splice(row, descNumber + 1);
        var top = this.master.tasks.splice(0, newRow);
        this.master.tasks = [].concat(top, blockToMove, this.master.tasks);
        //move on dom
        var rows = this.master.editor.element.find("tr[taskId]");
        var domBlockToMove = rows.slice(row, row + descNumber + 1);
        rows.eq(newRow).before(domBlockToMove);

        //recompute depends string
        this.master.updateDependsStrings();
    } else {
        this.master.setErrorOnTransaction(GanttMaster.messages["TASK_MOVE_INCONSISTENT_LEVEL"], this);
        ret = false;
    }
    return ret;
};

Task.prototype.moveDown = function () {
    //a row below must exist, and cannot move root task
    var row = this.getRow();
    if (row >= this.master.tasks.length - 1 || row == 0)
        return false;

    var ret = false;

    //find nearest brother
    var newRow;
    for (newRow = row + 1; newRow < this.master.tasks.length; newRow++) {
        if (this.master.tasks[newRow].level <= this.level)
            break;
    }

    //is brother
    if (this.master.tasks[newRow] && this.master.tasks[newRow].level == this.level) {
        ret = true;
        //find last desc
        for (newRow = newRow + 1; newRow < this.master.tasks.length; newRow++) {
            if (this.master.tasks[newRow].level <= this.level)
                break;
        }

        //compute descendant
        var descNumber = 0;
        for (var i = row + 1; i < this.master.tasks.length; i++) {
            var desc = this.master.tasks[i];
            if (desc.level > this.level) {
                descNumber++;
            } else {
                break;
            }
        }

        //move in memory
        var blockToMove = this.master.tasks.splice(row, descNumber + 1);
        var top = this.master.tasks.splice(0, newRow - descNumber - 1);
        this.master.tasks = [].concat(top, blockToMove, this.master.tasks);


        //move on dom
        var rows = this.master.editor.element.find("tr[taskId]");
        var aft = rows.eq(newRow - 1);
        var domBlockToMove = rows.slice(row, row + descNumber + 1);
        aft.after(domBlockToMove);

        //recompute depends string
        this.master.updateDependsStrings();
    }

    return ret;
};


//<%------------------------------------------------------------------------  LINKS OBJECT ---------------------------------------------------------------%>
function Link(taskFrom, taskTo, lagInWorkingDays) {
    this.from = taskFrom;
    this.to = taskTo;
    this.lag = lagInWorkingDays;
}


//<%------------------------------------------------------------------------  ASSIGNMENT ---------------------------------------------------------------%>
function Assignment(id, resourceId, roleId, effort) {
    this.id = id;
    this.resourceId = resourceId;
    this.roleId = roleId;
    this.effort = effort;
}


//<%------------------------------------------------------------------------  RESOURCE ---------------------------------------------------------------%>
function Resource(id, name) {
    this.id = id;
    this.name = name;
}


//<%------------------------------------------------------------------------  ROLE ---------------------------------------------------------------%>
function Role(id, name) {
    this.id = id;
    this.name = name;
}




