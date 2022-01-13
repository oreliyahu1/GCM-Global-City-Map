
$(document).ready( function() { 
	
	document.title = "GCM GROUP 7 / " + data.city.name + " / " + data.city.version.vMain + "." + data.city.version.vSub;
	
	$('#country').text($('#country').text().replace('x', data.city.country.shortName));
	$('#city').text($('#city').text().replace('x', data.city.name));
	$('#citycollection').text($('#citycollection').text().replace('x', data.city.version.vMain + "." + data.city.version.vSub));
	$('#downloadby').text($('#downloadby').text().replace('x', data.user.username));
		
	for(map in data.maps){
		$('.list-group').append(" <a href='#' id='" + map + "' class='list-group-item list-group-item-action'>" + data.maps[map].name + "<span class='badge'>"+data.maps[map].numberOfPois+"</span></a>");
		$("#"+map).data('move',data.maps[map].name);
	}	
		
	$('.list-group').on('click', 'a', function(e) {
        e.preventDefault()
		
		window.location.href = $(this).data('move') + "/index.html";
		
        $that = $(this);
        $that.parent().find('a').removeClass('active');
        $that.addClass('active');
    });
});

	