<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- to create axios api for view invoice-->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<meta charset="ISO-8859-1">
<title>Vender-Profile page</title>
<link rel="icon" href="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" >
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
     .footer{
     margin-top: 60px;
     background-color: #88ceec;
     }
     
     .container {
    margin-left: 90px;
    max-width: 86vw;
    margin-top: 60px;
    padding: 20px;
    background-color: #edf4f511;
   box-shadow: 10px 18px 16px rgb(128, 196, 230);
    border-radius: 8px;
    text-overflow: clip;
    
    

width: 200ch;
             border-right: 2px solid;
             border-bottom: 3px solid;
             border-top:1px solid;
            overflow: hidden;
            animation: type 2.5s steps(100);
              white-space: nowrap;

}

@keyframes type {
        from {
            width: 0;
             
        }
    }

     
     
 button{
 background-color: #007BFF;
    color: #fff;
    border: none;
    border-radius: 4px;
    padding: 10px 20px;
    cursor: pointer;
    margin-top: 10px;
    margin-left:0px;

}
 button:hover {
    background-color: #0056b3;
}

.scrollContainer {
  overflow-y: auto;
  height: 250px;
  position: relative;
  /* border: 1px solid red; */
  width: auto;
}

    </style>

</head>
<body>
     
      <nav class="navbar navbar-expand-lg bg-body-tertiary"  >
        <div class="container-fluid" style="background-color:  #88ceec; style="display: flex"">
              
            <div>
                <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="logo" style="height: 80px; width: 160px;">
            </div>
          <a class="navbar-brand" href="backToVenderProfile"  style="margin-left: 670px ; font-size: 35px; color:white;"> <b>Home</b></a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
        <button onclick="onOpenModalForUploadImage(`${venderEmaild}`)" > <img alt="UpIm" style="height:70px;width: 60px; border-radius: 50%; margin-left:0px ;margin-top: 1px;padding: 0px"
           src="display?fileName=${picPath == null ? 'temp.jpg' : picPath}">
            </button>
          <p style="margin-bottom: -0px;margin-left: 5px"; ><b>   ${venderEmaild}  </b></p>
          <a href="edit?email=${venderEmaild}" style="margin-bottom: 8px;margin-right: 0px"><button id="button"  onclick="edit()">Edit</button></a>
           <a href="index.jsp" style="margin-bottom: 8px;"><button id="button"  onclick="logOut()">LogOut</button></a>
         </div>
      </nav>
       
      
      <center><h3> <b> User_profile page </b></h3></center>
       <form action="onSearch" method="post" style="margin-left: 150vh;margin-top: -50px" >
         <select name="prodectType" placeholder="Search by ProdectType" style="border-width:85%;border: 3px solid;padding: 7px;border-radius: 7px;" >
         <option >Select</option>
         <option>Food and Beverages</option>
         <option>Electronics</option>
         <option>Furnitures</option>
         <option>Others</option>
         </select>
         <button type="submit">Search</button></a>
       </form>
      
     <div class="container">
     
      <marquee behavior="alternate" direction="right" scrollamount="20" > <h2 style="text-shadow: 0px 0px 0px ; color: rgb(16, 17, 17);"> <b> Required_Products</b></h2></marquee>
     
     <div class="scrollContainer">
       <table class="table" id="listingTable">
      <tr> 
      <th scope="col">Order ID</th>
      <th scope="col">Product Type</th>
      <th scope="col">Product</th>
      <th scope="col">Required Date</th>
      <th scope="col">Required Amount</th>
      <th scope="col">Location</th>
      <th scope="col">Status</th>
      <th scope="col">Action</th>
      <th scope="col">view</th>
      
      </tr>
      
      <c:forEach items="${pdtos}" var="pdto">
      <tr>
      <td>${pdto.orderId }</td>
      <td>${pdto.prodectType}</td>
      <td>${pdto.prodect}</td>
      <td>${pdto.requireddate}</td>
      <td>${pdto.totalRequired}</td>
  	  <td>${pdto.location}</td>
  	  <td>${pdto.status}</td>
      <td><button onclick="onOpenModal(`${pdto.orderId}`)" ${pdto.status eq "InvoiceSent" ? 'disabled="disabled" class="bg-light text-primary"':''} class="w3-button w3-green w3-large" style="margin-top: -5px"  >Send Invoice</button></td>
      
      <td><button onclick="onViewInvoiceModal(`${pdto.orderId}`)" class="w3-button w3-green w3-large" style="margin-top: -5px">view Invoice</button></td>

      </tr>
      </c:forEach>
      
      </table>
      </div>
     <!--  <a onclick="prevPage()" href='#' id="btn_prev" style="margin-left: 140vh"><button>Prev</button></a>
       <a onclick="nextPage()" href='#' id="btn_next"  style="margin-left: 00px"><button> Next</button></a>
   <b> page: <span id="page"></span></b>--> 
     </div>
     
     
     <!-- for block enable and disable -->
  <div id="id01" class="w3-modal" > 

 <!-- for block container -->
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px;border-radius: 10px;border-bottom-left-radius:30px;">

<!-- for block animaction -->
      <div class="w3-center"><br>
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
        <!-- <img src="img_avatar4.png" alt="Avatar" style="width:30%" class="w3-circle w3-margin-top"> -->
      </div>

      <form class="w3-container" action="generateInvoice" method="post">
        <div class="w3-section">
<!--         private VendorService vmanagementService; -->
         <label><b>OrderId</b></label> 
          <input class="w3-input w3-border w3-margin-bottom bg-light" readonly="readonly" type="text"  name="orderId"  id="orderId"> 	

           <label><b>Price</b></label> 
           <input class="w3-input w3-border w3-margin-bottom" type="number" placeholder="Enter Username" name="price" >
            
           <label><b>Delivery Charge</b></label> 
           <input class="w3-input w3-border" type="number" placeholder="Enter the Delivery Cahrge" name="delivarycharge" ><br>
            
          <label><b>Discription about price </b></label> 
           <input class="w3-input w3-border" type="text" placeholder="Enter the Discription about price" name="discription" ><br>
           
                
           
          <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">Generate Invoice</button>
<!--           <input class="w3-check w3-margin-top" type="checkbox" checked="checked"> Remember me -->
        </div>
                   
      </form>

      <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
        <button onclick="document.getElementById('id01').style.display='none'" type="button" class="w3-button w3-red" style="border-radius:10px;">Cancel</button>
<!--         <span class="w3-right w3-padding w3-hide-small">Forgot <a href="#">password?</a></span> -->
      </div>

    </div>
  </div>


  <!-- for block enable and disable -->
  <div id="id02" class="w3-modal" > 

 <!-- for block container -->
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px;border-radius: 10px;border-bottom-left-radius:30px;">

<!-- for block animaction -->
      <div class="w3-center"><br>
        <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
        <!-- <img src="img_avatar4.png" alt="Avatar" style="width:30%" class="w3-circle w3-margin-top"> -->
      </div>

      <form class="w3-container" action="generateInvoice" method="post">
        <div class="w3-section">
         <label><b>OrderId</b></label> 
          <input class="w3-input w3-border w3-margin-bottom bg-light"  type="text"  name="orderId"  readonly="readonly"  id="orderIdView"> 	

           <label><b>Price</b></label> 
           <input class="w3-input w3-border w3-margin-bottom" type="number" placeholder="Enter Username" readonly="readonly" name="price" id="viewPrice">
            
           <label><b>Delivery Charge</b></label> 
           <input class="w3-input w3-border" type="number" placeholder="Enter the Delivery Cahrge" name="delivarycharge"  readonly="readonly" id="viewdelivarycharge"><br>
            
          <label><b>Discription about price </b></label> 
           <input class="w3-input w3-border" type="text" placeholder="Enter the Discription about price" name="discription" readonly="readonly" id="viewdiscription" ><br>
           

                </div>
                   
      </form>

      <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
        <button onclick="document.getElementById('id02').style.display='none'" type="button" class="w3-button w3-red" style="border-radius:10px;">Close</button>
<!--         <span class="w3-right w3-padding w3-hide-small">Forgot <a href="#">password?</a></span> -->
      </div>

    </div>
  </div>
  
  
  
   <!--for image uplodae.. -->
    <!-- for block enable and disable -->
  <div id="id03" class="w3-modal" > 

 <!-- for block container -->
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:300px;border-radius: 10px;border-bottom-left-radius:30px;">

<!-- for block animaction -->
      <div class="w3-center"><br>
        <span onclick="document.getElementById('id03').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
        <!-- <img src="img_avatar4.png" alt="Avatar" style="width:30%" class="w3-circle w3-margin-top"> -->
      </div>

      <form class="w3-container" enctype="multipart/form-data" action="uploadUserImage" method="post">
        <div class="w3-section">
<!--         private VendorService vmanagementService; -->
         <label><b>userName</b></label> 
          <input class="w3-input w3-border w3-margin-bottom bg-light" readonly="readonly" type="text" readonly="readonly"  name="emailid" id="emailid"  >
           	
         <label><b>uploadFile</b></label>
         <input type="file" name="image">
           
                
           
          <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">Upload</button>
<!--           <input class="w3-check w3-margin-top" type="checkbox" checked="checked"> Remember me -->
        </div>
                   
      </form>

      <div class="w3-container w3-border-top w3-padding-16 w3-light-grey">
        <button onclick="document.getElementById('id03').style.display='none'" type="button" class="w3-button w3-red" style="border-radius:10px;">Cancel</button>
<!--         <span class="w3-right w3-padding w3-hide-small">Forgot <a href="#">password?</a></span> -->
      </div>

    </div>
  </div>

  

       
      <div class="footer" >
      <div>
        <!--<img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="logo" style="height: 80px; width: 160px; margin-left: 570px;">-->
        <p style="margin-left: 750px;"><b> follow us on</b></p> 
        
        <img style="margin-left: 700px; margin-top: -20px" alt="" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAD30lEQVR4AayShW4cMRCGLQgzHArKDK9xor5KnyDMSZmZmZmZRWWuKMy8e3y7U8/KvXG6PlR+6dM3Wtv/tElYulnzHXI918HnvQZ7PNfgGfdvz1XQEJzFtz14B++y+crCO+D2XoG97ssw475swhwuGWSOK26T3zX24luWbZbehTxe1uS+YOiuCzGwOB+3mA2yAvcFU8cO7Mrsf30R3Lz4nfMcL+eglZyNkm3QGXZhJ0snzrOw3nE61uc4zQs4wmKOxC3PaIszZA4/I2On6zSsY8ni4P9Kx6loX/WJMCCOk5G47dCZ+r79HLtxR8LfueN45F318TDEORYiW4TJHBcvbf8QhT/TJoRiJkQME2bCJvRpJnwaM8TbuX24Q/k3wS82VR0JwRwOB8nSjF5xJgRvhgxIEtEj3kngLibHcQzc1YdDetWhICSj8iD5UW8MUsX+PoBGdNzJ/qXqQHBv5f4AVHCEBX4yp1LMG26EIJ2I+2hpFn18J8OsuQi5lXv9MxV7/ZAul35HIY0k7cCduJtV7tN95bt0sNgdt5g1sjR/GrX/+E9+j8DyI35xV7eh6q/cpftY2Q5tDwfSoVxYj5jwf5Yf4qV0j+bknXtY2bbZZxxIh9KtlkERfjaD52QFcgdSvl17xkq3zP4u3TwDSAlZAZ2pgmeZgrtZ6aZpraRrGpR0kuVZFTpXv1eBu1lpx5RW3DEFFu1kmWzSM21AiehC04wWc/uUxorbpn4XtU6CiuLWKcvZ5HVPxN7ZQkZwNytqnnxW1DwBSGETWSabnPsSkjuV4G5W2DC+hwPJyCZdL/22ngLJYt7DihonfAW1Y2BRR5bJJhtvaeL9OFoJ7mZrGiC3sHb8b6vlcdswFATRL2ZCDagAX12H+3DOOWe7A8d2fFQZlsRMXpzu6z8kVnGdPcDgDbRJ5InP/nFB3lFOTMlck1TVuW8gD8wzcRO3FeQfFvfeQUbeQU5M2K0yOGBJXlVjcmaO1HFTsernecPZTd/c3ZQ0CeTM7M+S3L0MNWbpT/a94abql7ebXDrburidECiZa5LsspZUZO+kzNI2Z31r9JPsmlx7M2namzHZG7J1vaQkp6qBff09csYN3FKS6jt5w1mPA2stos8sCb/b6zFzIPMcduOG+kzORjpurUaBtRKRuRwSsz8LqnpWQuaoV8MAu9V3VF/MG9Zi2DQXAxrwUlhSENeZA7aWwyY/+bc1tkKu/hOXxkLwZswHVLlTUlINNW0mjFnswC71W+FtGLOd+9ps+7k2qw/MtEmSrlNZB+c6z5jBrPo3nZNjzXQm1FTrjgSp6dYDakr3oFd9U+9y1lO9tTZT6gAAAABJRU5ErkJggg==" style="margin-left: 250px;">
       
        <img  style="margin-top: -20px; margin-left:10px; border-radius:50px; width:35px;" alt="twiterlogo" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAA4CAMAAACfWMssAAAAV1BMVEX///8dofIAmvEAnPH2+/4GnvI0p/O22vq93vre7/3v+P7M5vvW6vwAmPHs9v6m0/mZzfiHxfdzvfbk8v1Bq/NpufUAlfGOyPcAkfBRsPR+wfZdtPTH4/udz3kPAAABLklEQVRIie2U2ZaDIAxAScLiVnHBOm39/++c0toptAZ65nHG+4bmSkwgQuzs/HuqZvyNZjUi0nBzx5qLWpaXB9IReBQaUUxHyYkTNvEDrWCFiI4Fm9f1fZTNTPBEGdmVjIgAaIN16IF2Xx23ow+kU/tY1pEIB9YT7l6I86qaSFSG9YR91HAq5JsIvOers36dyJ3trD4WxyBUUew5XpPYdXFw+IuXVKbEeqDmhNjzHhB/bq4XAXkRq1RxzIHN9JTyfOtoW6RE+2/ITm+bGc9ot72hzYgCtgurc54oNwuL7NAITHgvD6Wa/8S6l/pQ6rRFxCc218IfFh0li0NO8He3qudYU5hthJA9XodgnCVePhrjbR/eXkU4NXlpZekdoYf0YNjJzVCVTdnmw3Z2/jjfQWEJXwOeKIcAAAAASUVORK5CYII=">
        
        <img style="margin-top: -20px; margin-left:10px;" alt="instalogo" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAMAAABF0y+mAAABWVBMVEVyHVFHcEx0Fvp8Fv1yFP1/FfyLCOyfBOmtAeS7AuDHAd3UANjZAs7rD6xyFvzjANTqAMHoA7l2F/yPFfx8GPmfD/zFF/XVWujoZuLyZt3pRt/yGdHyA8j1AKTViPj/9Pr////8g9f9A7r3DZz5vO794Pf+ruj9B6uzFPv7JLv+AJr+BI/+YMP/1vD+AIThG+n9GZn/vd3/YZz+AXP+GoT/r8r+AmT/b5z+HHH+I3f+G2P/ur7+FFD+Klb+fYv/wcr+LUf+LWX+AFn+Nzr+Ohz+RT7+inP+Si7/ztD+TxL/zbv+Wy//9fH+WgL+c1X+ZyX+PUv+Bzv+VXD+cxr/6eL+aQT+eAX+nDj+gwD+gxH+fSL+rYn+jwD+iwL+XhX/3rn/1bH9ZR/+lwP+nwH+xHH8ZC3+qAH+sAD+wAP+x1P+zGb+pl79mQn+uAD9J3T+ygD9O1n8pRL9twuaEkMNAAAAc3RSTlMBAF3G///////////GW9j//9nY/1r///////////7//////8b/////////////////////////////////////////////////////////////////////////////////xf///1v////////Y/9j/2VzGSus+GgAAAeNJREFUeAFFjEWiU0EQRc/tqu74V9wZ4TBiB6wG3wpzVoQ7zHCXeELypHH+KVchJAESaEs0IUv0Zv+7fwLgSAOk9ZFghcnfDw4AAg18cyhWCKNVob9NCZjQLWVrEGhJbJGDoCBpMwCrJTETAKBWgdeRGb4dmJW56PdI/KU1K4X15N+AFsW0+3/WVm7Pa0Pmu4Fh7HcbRIjMIhr3FItUGAH4SNZKjK32YjKpK3Iv0yUY0b8g0Vs0oK+8wbeyMBAQwVeo44R1z7XWq6rqpn5jiTmAeQO3iceg9zkYzKvc71iEmMFbqCJJ4tDcKFrLjX5D/MEdq8GpIUUgd/tgGboTgiUDPDZ44SXNpr0gWRcAgqEmEPJRNVPOxYaOth0oAHdCxWoOzslH+RCvlafYMgFUHvAIHoCjz16io80BrH/j0AAcJ9KWKkhn5sRyGjsDtr/sqyrsWNO1cW9/cDNLZgHLSzVvn+wMq9qbKKwy7TiIaKTW56pzW6Oa+N1OKEQdvvl2RyOE4DEOZurd1cHNeab27tyjOPfhFlucXP04ovF97B6jTOzdJwFLaNAff7N6c8incH1VpiSR67q2VgjzUU7JgbMTiQtFKmgAsCAuczlflmVZn7iGBFc1awB/p4tqUS3LDb8OPwBrSrZIO/KHZwAAAABJRU5ErkJggg==">
        <img style="margin-top: -20px; margin-left:10px" alt="linkedinlogo" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAB20lEQVRYhWP8//8/w0ACpgG1nYGBgQXGaNh106Jx160lDAwMyjS28269m1pMg5v6CQYGBgZGWBQwlmy+QwfL4Y743+OrwsCAGgX0shzFLhZcKjzURRnSLRUYGBgYGGYef8Cw4+ZrmrgEqwM81EUZtqdawPkBOhIMnrNP0MQRWHMBzOeExGjmAHoCrA6YefwBUWLUAFjTwI6brxk8Z58YuEQIcwSplioIcjKIcLMxvPn6i+HJxx8Mf/4RLuZxOuB/jy8Kn7FkM4b4gbtvGTxnn2Aod1JhyLFWZBDhZoPL/fj9l2HFhWcMlduuM7z4/JN0BxADeNiYGXanWzDYKApjyHGwMjMkmMoyWMgLMlhOOszw4ccfrGZQlAtMZAWwWo4MNMR4GLKtFXHKUy0bvvn6i+HBu28MX35i+jRETxKnPoqigIGBgeHPv38MqasuMiw484SBgYGBQYCDhWF9oimDg7IIXI2OJC9O/RSHwJSjD+CWMzAwMHz48Yehdc9tFDUsTLitodgBK88/xRC78+Yr0fopdgC2LPbh+2/6OeDB+++YDsCR5WjiAErBqANGHYDcLKdrF+l/jy8jA8MgCAFkB9ylo71wu+AOqHdTi6GTI+7uz7SMgHEYR3zvGABAiZoIrctOXgAAAABJRU5ErkJggg==">
        <p style="margin-left: 650px; margin-top: 0px">Copyright @ 2024, All Right Reserved</p>
        
      </div>

     <script type="text/javascript">
     function onOpenModal(orderId){
		 document.getElementById('orderId').value=orderId; 
    	 document.getElementById('id01').style.display='block';  	 
     }
     
     
     function onViewInvoiceModal(orderId){

//     	 const xhttp= new XMLHttpRequest();
//          xhttp.open("GET","http://localhost:8080/PinXworkz/getInvoice/5ZEKL5");
//          xhttp.send();

//          xhttp.onload=function(){
//          console.log(this);
//          }
     	 /* const promise=fetch("http://localhost:8080/PinXworkz/getInvoice/"+orderId ,{
     		  headers: {
   			    'Accept': 'application/json',
     			    'Content-Type': 'application/json'
     			  }
     			} ); */
     const resp=axios("http://localhost:8080/PinXworkz/getInvoice/"+orderId);
     			resp.then(function (result){
     				console.log(result.data.orderId);
    				 document.getElementById('orderIdView').value=result.data.orderId; 
    				 document.getElementById('viewPrice').value=result.data.price; 
    				 document.getElementById('viewdelivarycharge').value=result.data.delivarycharge;
    				 document.getElementById('viewdiscription').value=result.data.discription;
     				})
    	 document.getElementById('id02').style.display='block'; 
     }
     
     //for uplad the image 
     function onOpenModalForUploadImage(emailid){
		 document.getElementById('emailid').value=emailid; 
    	 document.getElementById("id03").style.display='block';  	 
     }
     
     function logOut() {
    	 alert("you are Profile is getting Logout")
		
	}
     
/*     //for pagination..
     var current_page = 1;
var records_per_page = 7;
var l = document.getElementById("listingTable").rows.length

function prevPage()
{

if (current_page > 1) {
   current_page--;
   changePage(current_page);
}
}

function nextPage()
{
if (current_page < numPages()) {
   current_page++;
   changePage(current_page);
}
}

function changePage(page)
{
var btn_next = document.getElementById("btn_next");
var btn_prev = document.getElementById("btn_prev");
var listing_table = document.getElementById("listingTable");
var page_span = document.getElementById("page");

// Validate page
if (page < 1) page = 1;
if (page > numPages()) page = numPages();

[...listing_table.getElementsByTagName('tr')].forEach((tr)=>{
   tr.style.display='none'; // reset all to not display
});
listing_table.rows[0].style.display = ""; // display the title row

for (var i = (page-1) * records_per_page + 1; i < (page * records_per_page) + 1; i++) {
   if (listing_table.rows[i]) {
       listing_table.rows[i].style.display = ""
   } else {
       continue;
   }
}

page_span.innerHTML = page + "/" + numPages();

if (page == 1) {
   btn_prev.style.visibility = "hidden";
} else {
   btn_prev.style.visibility = "visible";
}

if (page == numPages()) {
   btn_next.style.visibility = "visible";
} else {
   btn_next.style.visibility = "visible";
}
}

function numPages()
{
return Math.ceil((l - 1) / records_per_page);
}

window.onload = function() {
changePage(current_page);
};
*/
     </script>
     
</body>
</html>