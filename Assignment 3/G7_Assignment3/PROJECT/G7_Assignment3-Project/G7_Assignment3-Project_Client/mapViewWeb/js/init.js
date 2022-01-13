$(document).ready( function() {

	imageMap = new Image();
	imageMap.src = "data:image/png;base64, " + data.map.base64_image;
	imageMap.onload = function() {
		
		$('#imgMap').attr('src',imageMap.src);
		document.title = data.map.city.name + " / " + data.map.name;
		$('#country').text($('#country').text().replace('x', data.map.city.country.shortName));
		$('#city').text($('#city').text().replace('x', data.map.city.name));
		$('#mapversion').text($('#mapversion').text().replace('x', data.map.mapVer.vMain + "." + data.map.mapVer.vSub));
		$('#mapname').text($('#mapname').text().replace('x', data.map.name));
		$('#mapdescription').text($('#mapdescription').text().replace('x', data.map.description));
		
		for(poimap in data.poimaps){
			$('#map').append('<div class="marker" id="'+poimap+'" data-coords="'+[data.poimaps[poimap].cordinates.x - 2, data.poimaps[poimap].cordinates.y-27]+'">'+
				'<h3>'+data.poimaps[poimap].name + '</h3>'+
				'<div class="wrapperstats"><span class="item-title">Accessible: </span> ' + data.poimaps[poimap].accessible + '</div>	'+
				'<div class="wrapperstats"><span class="item-title">Category: </span> ' + ((data.poimaps[poimap].category!=null) ? data.poimaps[poimap].category.category : "-") + '</div>	'+
				'<div class="wrapperstats"><span class="item-title">Description: </span> ' + data.poimaps[poimap].description + '</div>	'+
				'</div>');
			$('.list-group').append(' <a href="#" rel="'+poimap+'" id="'+poimap+'" class="list-group-item list-group-item-action">'+data.poimaps[poimap].name+'</a>');
		}	
		$('#map').css({width:'100%', height:'80vh'});
		
		$('#map').craftmap({
			fullscreen: false,
			image: {
				width: imageMap.width,
				height: imageMap.height
			}
		});
	}	
});