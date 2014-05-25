<!DOCTYPE html>
<html lang="en">
	<head>
		<title>工井三维视图</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
		<style>
			body {
				color: green;
				font-family:Monospace;
				font-size:35px;
				text-align:center;
				font-weight: bold;
				background-color: black;
				margin: 0px;
				overflow: hidden;
			}
		</style>
	</head>
	<body >
	<div id="prompt" style="width:95%; height:10%; bottom:0; text-align:center; position:absolute;">工井三维剖面显示</div>
	<script src="../3D/build/three.min.js"></script>
	<script src="../js/controls/OrbitControls.js"></script>	
	<script>
	var container,camera,controls,scene,renderer;
	init();
	animate();
	onWindowResize();
	function init(){		
		// Grab our container div
        var container = document.createElement( 'div' );
        document.body.appendChild(container)
 		// Put in a camera
        camera = new THREE.PerspectiveCamera( 70, 1000 / 1000, 1, 1000 );
        camera.position.set( 0, 20,10 );
	/*	// Set up the FP controls
		var control = new THREE.FirstPersonControls( this.camera );
		control.movementSpeed = 13;
		control.lookSpeed = 0.07;
		control.noFly = true;
		control.lookVertical = true;	
		var clock = new THREE.Clock();
		control.update( clock.getDelta() );
		*/
		//鼠标控制
        controls = new THREE.OrbitControls( camera );
	    controls.addEventListener( 'change', render );
	    
        // Create the Three.js renderer, add it to our div
	    renderer = new THREE.WebGLRenderer( { antialias: true } );
	    renderer.setSize(1000, 1000);
	    container.appendChild( renderer.domElement );

	    scene = new THREE.Scene();
        // Create a directional light to show off the object
        /*
		light = new THREE.DirectionalLight( 0xffffff );
		light.position.set( 0, 0, 0 );
		scene.add( light );
		light = new THREE.DirectionalLight( 0x002288 );
		light.position.set( -1, -1, -1 );
		scene.add( light );
		light = new THREE.AmbientLight( 0xFFFF00 );
		scene.add( light );
		*/
		//添加光线
		var light = new THREE.PointLight( 0xffffff, 1, 200);
		light.position.set(0, 20, 20);
		this.scene.add(light);

		light = new THREE.PointLight( 0xffffff, 1, 200);
		light.position.set(20, 0, 0);
		this.scene.add(light);

		var amb = new THREE.AmbientLight( 0x808080, 1);
		this.scene.add(amb);
        // Create a shaded, texture-mapped cube and add it to the scene
        // First, create the texture map

        //添加工井剖面墙
		var geometry = new THREE.CubeGeometry(1, 40, 40);
        var mapUrl = "../images/23.png";
        var map = THREE.ImageUtils.loadTexture(mapUrl);
        map.wrapS = map.wrapT = THREE.RepeatWrapping;
    	map.repeat.set(1,1);     
        var material = new THREE.MeshPhongMaterial({ map: map });
        var mesh = new THREE.Mesh(geometry, material);
		mesh.position.set(-20, 20, 0);
		scene.add( mesh );

		var geometry = new THREE.CubeGeometry(1, 40, 40);
		var material = new THREE.MeshLambertMaterial({ map : map});
		var cube = new THREE.Mesh(geometry, material);
		cube.position.set(20, 20, 0);
		scene.add( cube );

		var geometry = new THREE.CubeGeometry(40, 40, 1);
		var material = new THREE.MeshLambertMaterial({ map : map});
		var cube = new THREE.Mesh(geometry, material);
		cube.position.set(0, 20, -20);
		scene.add( cube );

		var geometry = new THREE.CubeGeometry(40, 40, 1);
		var material = new THREE.MeshLambertMaterial({ map : map});
		var cube = new THREE.Mesh(geometry, material);
		cube.position.set(0, 20, 20);
		scene.add( cube );

		//添加管孔
		
		var geometry = new THREE.CubeGeometry(3, 3, 1.1);
		var map = THREE.ImageUtils.loadTexture('../images/12.png');
    	map.wrapS = map.wrapT = THREE.RepeatWrapping;
   		map.repeat.set(1,1);
		var material = new THREE.MeshLambertMaterial({ map : map});
		var mesh = new THREE.Mesh(geometry, material);
		mesh.position.set(10, 10, 20);
		scene.add(mesh);
		
		draw_front_hole();
		draw_behind_hole();
		draw_left_hole();
		draw_right_hole();
/*
		var geometry = new THREE.CubeGeometry(1.1, 3, 3);
		var material = new THREE.MeshLambertMaterial({ map : map});
		var mesh = new THREE.Mesh(geometry, material);
		mesh.position.set(-20.9, 13, 0);
		scene.add(mesh);


		var geometry = new THREE.CubeGeometry(1.1, 3, 3);
		var material = new THREE.MeshLambertMaterial({ map : map});
		var mesh = new THREE.Mesh(geometry, material);
		mesh.position.set(10, 10, 0);
		scene.add(mesh);
		*/
		//前剖面管孔
		/*var pipe_holex = new Array();
		var pipe_holey = new Array();
		var z = 20;
		for(var i=0;i<3;i++){
			pipe_holex[i] = i*3;
			for(var j=0;j<3;j++){
				pipe_holey[j] = j*3+10;
				var geometry = new THREE.CubeGeometry(3, 3, 1.1);
				var material = new THREE.MeshLambertMaterial({ map : map});
				var mesh = new THREE.Mesh(geometry, material);
				mesh.position.set(pipe_holex[i],pipe_holey[j], z);
				scene.add(mesh);	
			}
		}
		*/
		//添加工井地面
		geometry = new THREE.PlaneGeometry(66, 66, 66, 66);
		var map = THREE.ImageUtils.loadTexture('../images/Mercury.jpg');
    	map.repeat.set(1,1);
  	 	map.wrapS = map.wrapT = THREE.MirroredRepeatWrapping;
   		material = new THREE.MeshLambertMaterial({ambient:0xffffff, map : map});
		cube = new THREE.Mesh(geometry, material);
		cube.rotation.x = -Math.PI / 2;
		cube.position.y = -.01;
		scene.add( cube );
		//添加工井上面
		geometry = new THREE.PlaneGeometry(0, 0, 0, 0);
		var map = THREE.ImageUtils.loadTexture('../images/ball_texture.jpg');
    	map.repeat.set(1,1);
  	 	map.wrapS = map.wrapT = THREE.MirroredRepeatWrapping;
   		material = new THREE.MeshLambertMaterial({ambient:0xffffff, map : map});
		cube = new THREE.Mesh(geometry, material);
		cube.rotation.x = -Math.PI / 2;
		cube.position.y = 40;
		scene.add( cube );
        window.addEventListener( 'resize', onWindowResize, false );
        
	}
	function update(){

	}
	function animate() {
		requestAnimationFrame( animate );
		render();
	}
	function onWindowResize() {
		camera.aspect = window.innerWidth / window.innerHeight;
		camera.updateProjectionMatrix();
		renderer.setSize( window.innerWidth, window.innerHeight );
		render();
	}
	
	function render() {
		renderer.render( scene, camera );
	}
	//画工井前剖面管孔
	function draw_front_hole(){	
		var pipe_holex = new Array();
		var pipe_holey = new Array();
		var z = 20;
		for(var i=0;i<3;i++){
			pipe_holex[i] = i*3;
			for(var j=0;j<3;j++){
				pipe_holey[j] = j*3+10;
				var geometry = new THREE.CubeGeometry(3, 3, 1.1);
				var map = THREE.ImageUtils.loadTexture('../images/12.png');
		    	map.wrapS = map.wrapT = THREE.RepeatWrapping;
		   		map.repeat.set(1,1);
				var material = new THREE.MeshLambertMaterial({ map : map});
				var mesh = new THREE.Mesh(geometry, material);
				mesh.position.set(pipe_holex[i],pipe_holey[j], z);
				scene.add(mesh);	
			}
		}
	}
	//画工井后剖面管孔
	function draw_behind_hole(){	
		var pipe_holex = new Array();
		var pipe_holey = new Array();
		var z = -20;
		for(var i=0;i<3;i++){
			pipe_holex[i] = i*3;
			for(var j=0;j<3;j++){
				pipe_holey[j] = j*3+10;
				var geometry = new THREE.CubeGeometry(3, 3, 1.1);
				var map = THREE.ImageUtils.loadTexture('../images/12.png');
		    	map.wrapS = map.wrapT = THREE.RepeatWrapping;
		   		map.repeat.set(1,1);
				var material = new THREE.MeshLambertMaterial({ map : map});
				var mesh = new THREE.Mesh(geometry, material);
				mesh.position.set(pipe_holex[i],pipe_holey[j], z);
				scene.add(mesh);	
			}
		}
	}
	//画工井后左面管孔
	function draw_left_hole(){	
		var x = -20;
		var pipe_holey = new Array();
		var pipe_holez = new Array();
		for(var i=0;i<3;i++){
			pipe_holez[i] = i*3;
			for(var j=0;j<3;j++){
				pipe_holey[j] = j*3+10;
				var geometry = new THREE.CubeGeometry(1.1, 3, 3);
				var map = THREE.ImageUtils.loadTexture('../images/12.png');
		    	map.wrapS = map.wrapT = THREE.RepeatWrapping;
		   		map.repeat.set(1,1);
				var material = new THREE.MeshLambertMaterial({ map : map});
				var mesh = new THREE.Mesh(geometry, material);
				mesh.position.set(x,pipe_holey[j], pipe_holez[i]);
				scene.add(mesh);	
			}
		}
	}
	//画工井右剖面管孔
	function draw_right_hole(){	
		var x = 20;
		var pipe_holey = new Array();
		var pipe_holez = new Array();
		for(var i=0;i<3;i++){
			pipe_holez[i] = i*3;
			for(var j=0;j<3;j++){
				pipe_holey[j] = j*3+10;
				var geometry = new THREE.CubeGeometry(1.1, 3, 3);
				var map = THREE.ImageUtils.loadTexture('../images/12.png');
		    	map.wrapS = map.wrapT = THREE.RepeatWrapping;
		   		map.repeat.set(1,1);
				var material = new THREE.MeshLambertMaterial({ map : map});
				var mesh = new THREE.Mesh(geometry, material);
				mesh.position.set(x,pipe_holey[j], pipe_holez[i]);
				scene.add(mesh);	
			}
		}
	}
	</script>
	</body>
</html>
