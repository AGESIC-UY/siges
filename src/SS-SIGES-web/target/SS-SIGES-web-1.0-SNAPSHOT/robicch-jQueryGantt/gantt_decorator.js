jQuery.JST.loadDecorator("ASSIGNMENT_ROW", function(assigTr, taskAssig) {
    var resEl = assigTr.find("[name=resourceId]");
    for (var i in taskAssig.task.master.resources) {
        var res = taskAssig.task.master.resources[i];
        var opt = jQuery("<option>");
        opt.val(res.id).html(res.name);
        if (taskAssig.assig.resourceId == res.id)
            opt.attr("selected", "true");
        resEl.append(opt);
    }


    var roleEl = assigTr.find("[name=roleId]");
    for (var i in taskAssig.task.master.roles) {
        var role = taskAssig.task.master.roles[i];
        var optr = jQuery("<option>");
        optr.val(role.id).html(role.name);
        if (taskAssig.assig.roleId == role.id)
            optr.attr("selected", "true");
        roleEl.append(optr);
    }

    if (taskAssig.task.master.canWrite) {
        assigTr.find(".delAssig").click(function() {
            var tr = jQuery(this).closest("[assigId]").fadeOut(200, function() {
                jQuery(this).remove();
            });
        });
    }


});