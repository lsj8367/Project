var vmap;
  var selectMarker;
  var imgPath = '/resources/img/'; //이미지 경로
  vw.ol3.MapOptions = {
      basemapType: vw.ol3.BasemapType.GRAPHIC
    , controlDensity: vw.ol3.DensityType.EMPTY
    , interactionDensity: vw.ol3.DensityType.BASIC
    , controlsAutoArrange: true
    , homePosition: vw.ol3.CameraPosition
    , initPosition: vw.ol3.CameraPosition
   }; 
  
  vmap = new vw.ol3.Map("vmap",  vw.ol3.MapOptions);
  vmap.getView().setCenter(ol.proj.transform([127.8945727, 36.3505553], "EPSG:4326", "EPSG:3857"));
  vmap.getView().setZoom(5);
  
  
  
  vmap.on('pointermove', function(evt) {
	  var feature = vmap.forEachFeatureAtPixel(evt.pixel, function(feature,layer) {
		  if (layer != null && layer.className == 'vw.ol3.layer.Marker') {
			  $('#param').val('');
			  $('#param').val(feature.get('id'));
			  selectMarker = feature;
		  } else {
			  return false;
		  }
	  });
  });	  
  
  
  
  var markerLayer;
  function addMarkerLayer(data) {
    markerLayer = new vw.ol3.layer.Marker(vmap);
    vmap.addLayer(markerLayer);
    addMarker(data);
   }
  
  function removeAllMarker() {
	   if(markerLayer == null){
	    
	   } else {
	    this.markerLayer.removeAllMarker();
	   }
  }
  
  
  
  function addMarker(data) {
	//서울 
   vw.ol3.markerOption = {
    x : 127.0339988, //경도
    y : 37.5637733, //위도
    epsg : "EPSG:4326",
    title : `${data[0].city}`,
    contents : `기온 ${data[0].temp}°C <br> 강수량 :` + `${data[0].rain == 0 ? '-' : `${data[0].rain}<small>mm</small>`}`,
    iconUrl : imgPath + 'marker-blue.png', 
    text : {
        offsetX: 0.5, //위치설정
        offsetY: 20,   //위치설정
        //text: '테스트마커1'
    },
	attr: {"id":"maker01","name":"속성명1"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //부산 
   vw.ol3.markerOption = {
	   x : 128.9717121,
	   y : 35.1643694,
	   epsg : "EPSG:4326",
	   title : `${data[1].city}`,
	   contents : `기온 ${data[1].temp}°C <br> 강수량 :` + `${data[1].rain == 0 ? '-' : `${data[1].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker-blue.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
 //대구 
   vw.ol3.markerOption = {
    x : 128.4266122,
    y :  35.8796687,
    epsg : "EPSG:4326",
    title : `${data[2].city}`,
    contents : `기온 ${data[2].temp}°C <br> 강수량 :` + `${data[2].rain == 0 ? '-' : `${data[2].rain}<small>mm</small>`}`,
    iconUrl : imgPath + 'marker-blue.png', 
	
	attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //인천 
   vw.ol3.markerOption = {
    x : 126.5842625,
    y :  37.3644673,
    epsg : "EPSG:4326",
    title : `${data[3].city}`,
    contents : `기온 ${data[3].temp}°C <br> 강수량 :` + `${data[3].rain == 0 ? '-' : `${data[3].rain}<small>mm</small>`}`,
    iconUrl : imgPath + 'marker-gold.png', 
	
	attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //광주 
   vw.ol3.markerOption = {
    x : 126.7542617,
    y :  35.1596687,
    epsg : "EPSG:4326",
    title : `${data[4].city}`,
    contents : `기온 ${data[4].temp}°C <br> 강수량 : ` + `${data[4].rain == 0 ? '-' : `${data[4].rain}<small>mm</small>`}`,
    iconUrl : imgPath + 'marker-green.png', 
	
	attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //대전
   vw.ol3.markerOption = {
	   x : 127.3887123,
	   y : 36.3730182,
	   epsg : "EPSG:4326",
	   title : `${data[5].city}`,
	   contents : `기온 ${data[5].temp}°C <br> 강수량 : ` + `${data[5].rain == 0 ? '-' : `${data[5].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker.png',
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //울산 
   vw.ol3.markerOption = {
	   x : 129.2114122,
	   y :  35.561969,
	   epsg : "EPSG:4326",
	   title : `${data[6].city}`,
	   contents : `기온 ${data[6].temp}°C <br> 강수량 : ` + `${data[6].rain == 0 ? '-' : `${data[6].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker.png', 
		
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //세종
   vw.ol3.markerOption = {
	   x : 127.220475,
	   y : 36.5362732,
	   epsg : "EPSG:4326",
	   title : `${data[7].city}`,
	   contents : `기온 ${data[7].temp}°C <br> 강수량 : ` + `${data[7].rain == 0 ? '-' : `${data[7].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker-green.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   // 8번은 경기라서 제외
   //춘천
   vw.ol3.markerOption = {
	   x : 127.6973303,
	   y : 37.8687948,
	   epsg : "EPSG:4326",
	   title : `${data[9].district}`,
	   contents : `기온 ${data[9].temp}°C <br> 강수량 : ` + `${data[9].rain == 0 ? '-' : `${data[9].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //원주 
   vw.ol3.markerOption = {
	   x : 128.112,
	   y :  37.3499794,
	   epsg : "EPSG:4326",
	   title : `${data[10].district}`,
	   contents : `기온 ${data[10].temp}°C <br> 강수량 : ` + `${data[10].rain == 0 ? '-' : `${data[10].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker-blue.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
 //강릉
   vw.ol3.markerOption = {
	   x : 128.8649803,
	   y :  37.7636948,
	   epsg : "EPSG:4326",
	   title : `${data[11].district}`,
	   contents : `기온 ${data[11].temp}°C <br> 강수량 : ` + `${data[11].rain == 0 ? '-' : `${data[11].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker-gold.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //속초 
   vw.ol3.markerOption = {
	   x : 128.5535803,
	   y :  38.2046448,
	   epsg : "EPSG:4326",
	   title : `${data[12].district}`,
	   contents : `기온 ${data[12].temp}°C <br> 강수량 : ` + `${data[12].rain == 0 ? '-' : `${data[12].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker-green.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   
 //군산
   vw.ol3.markerOption = {
	   x : 126.6935404,
	   y : 35.9658835,
	   epsg : "EPSG:4326",
	   title : `${data[13].district}`,
	   contents : `기온 ${data[13].temp}°C <br> 강수량 : ` + `${data[13].rain == 0 ? '-' : `${data[13].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker-green.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
 //목포
   vw.ol3.markerOption = {
	   x : 126.3851803,
	   y :  34.802795,
	   epsg : "EPSG:4326",
	   title : `${data[14].district}`,
	   contents : `기온 ${data[14].temp}°C <br> 강수량 : ` + `${data[14].rain == 0 ? '-' : `${data[14].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);

   
   //여수
   vw.ol3.markerOption = {
	   x : 127.6568303,
	   y :  34.752495,
	   epsg : "EPSG:4326",
	   title : `${data[15].district}`,
	   contents : `기온 ${data[15].temp}°C <br> 강수량 : ` + `${data[15].rain == 0 ? '-' : `${data[15].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker-blue.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);
   
   //제주
   vw.ol3.markerOption = {
	   x : 126.5125272,
	   y : 33.3769611,
	   epsg : "EPSG:4326",
	   title : `${data[16].city}`,
	   contents : `기온 ${data[16].temp}°C <br> 강수량 : ` + `${data[16].rain == 0 ? '-' : `${data[16].rain}<small>mm</small>`}`,
	   iconUrl : imgPath + 'marker-gold.png', 
	   
	   attr: {"id":"maker02","name":"속성명2"}	
   };
   markerLayer.addMarker(vw.ol3.markerOption);

  }