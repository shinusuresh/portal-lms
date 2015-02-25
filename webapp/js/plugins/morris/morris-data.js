$(function() {

   Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
            label: "Leaves Left",
            value: 20
        }, {
            label: "Leaves Taken",
            value: 2
        }],
        
        colors:["#5CB85C",'#D9534F',"#00FF00"],
        resize: true
    });

   
});
