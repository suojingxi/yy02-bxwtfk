require.config({
	baseUrl: $ctx,
	paths: {
		text: "static/lib/requirejs/text",
		css: "static/lib/requirejs/css",
		jquery: "static/lib/jquery/jquery-1.11.3",
		timecontrol:"static/lib/timecontrol/jquery-1.7.1.min",
		'jquery.custom':"static/lib/timecontrol/jquery-ui-1.8.18.custom.min",
		'jquery.timepicker':"static/lib/timecontrol/jquery-ui-timepicker-addon",
		'jquery.datepicker':"static/lib/timecontrol/jquery.ui.datepicker-zh-CN",
		knockout: "static/lib/knockout/knockout-3.2.0.debug",
		ujs: "static/lib/uui/js/u",
		jqueryform: "static/lib/jquery-form/jquery.form",
		wizard:"static/lib/jquery-bootstrap-wizard/jquery.bootstrap.wizard",
		director:"static/lib/director/director",
		'jquery.file.upload' : "static/lib/juqery-file-upload/9.9.2/js/jquery.fileupload",
		'jquery.ui.widget':"static/lib/jquery-ui/jquery.ui.widget",
		'jquery.iframe.transport':"static/lib/jquery-iframe-transport/jquery.iframe-transport",
		dhtmlxcommon:'static/lib/dhtmlxTree/dhtmlxcommon',
		dhtmlxtree:'static/lib/dhtmlxTree/dhtmlxtree',
		'jquery-ui-1.8': 'static/lib/jquery-ui-1.8/js/jquery-ui-1.8.18.custom.min',
		error:'static/js/error',
		mvvc:'static/js/mvvc'
	},
	shim: {
		ujs:{
			deps: ["jquery"]
		},
		'jquery.file.upload':{
			deps: ["jquery","jquery.ui.widget","jquery.iframe.transport","css!static/lib/juqery-file-upload/9.9.2/css/jquery.fileupload.css"]
		},
		'jquery.alerts':{
			deps:['alerts','jquery.draggable','jquery.alerts']
		}
	}
});