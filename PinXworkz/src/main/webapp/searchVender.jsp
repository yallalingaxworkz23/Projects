<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
 
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1">
      <title>Admin page</title>
      <link rel="icon" href="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png">
      
      
      <!-- to create axios api for view invoice-->
      <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
      <link rel="icon" href="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
      <style>
        .footer {
        
          margin-top: 60px ;
          background-color: #88ceec;
        }
        
             .container {
    margin-left: 90px;
    max-width: 86vw;
    margin-top: 20px;
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
        
        

        button {
          background-color: #007BFF;
          color: #fff;
          border: none;
          border-radius: 4px;
          padding: 10px 20px;
          cursor: pointer;
          margin-top: 10px;
          margin-left: 0px;
          

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

      <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid" style="background-color:  #88ceec;">

          <div>
            <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="logo"
              style="height: 80px; width: 160px;">
          </div>
          
         
          <!--<a class="navbar-brand" href="#" style="margin-left: 600px ; font-size: 35px; color:white;">
            <b>Home</b></a>-->
          <!--           <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation"> -->
          <!--             <span class="navbar-toggler-icon"></span> -->
          <!--           </button> -->
          <img alt="person" style="height:40px;width: 40px; border-radius: 50%; margin-left:800px ;margin-top: 5px"
            src="data:image/jpeg;base64,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">
          <p style="margin-bottom: -0px;margin-left: 5px" ;><b> ${admimEmailid}</b></p>
          <a href="requiredProdect.jsp" style="margin-bottom: 8px;margin-right: 0px"><button
              id="button">add requirement</button></a>
          <a href="index.jsp" style="margin-bottom: 8px;"><button id="button" onclick="logOut()">LogOut</button></a>
        </div>
      </nav>
      
       <div>
          <button class="w3-button w3-blue w3-large"  style="margin-left: 10px"> <a href="viewInvoice"  style="text-decoration: none;color: white;"> View Invoice's </a></button>
         </div>
          <div>
           <button class="w3-button w3-blue w3-large" style="margin-left:170px; margin-top: -70px"> <a href="viewForProducts"  style="text-decoration: none;color: white;"> View Products</a> </button>
           </div>

      <center>
        <h3> <b style="color:black"> Admin_Profile_Page </b></h3>
      </center>
      <form action="tofindTheVender" method="Post"   style="margin-left: 150vh;margin-top: -50px" > <input type="search" name="emailid" placeholder="Search by emailId" style="border-width:85%;border: 3px solid;padding: 7px;border-radius: 7px;" ></input> <button type="submit" onclick="onEditModal()">Search</button></form>

  <div class="container">
  <marquee behavior="alternate" direction="right" scrollamount="20" > <h2 style="text-shadow: 0px 0px 0px ; color: rgb(16, 17, 17);"> <b> Venders_List</b></h2></marquee>
  
  <div class="scrollContainer">
      <table class="table"  id="listingTable">
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Name</th>
          <th scope="col">Phone</th>
          <th scope="col">Service</th>
          <th scope="col">GST</th>
          <th scope="col">Emailid</th>
          <th scope="col">Website</th>
          <th scope="col">Status</th>
          <th scope="col">action</th>
        </tr>
        <!--       by using jstl to do foreach operaction -->
      
          <tr>
            <td>${vdto.id}</td>
            <td>${vdto.name}</td>
            <td>${vdto.contactno}</td>
            <td>${vdto.servicetype}</td>
            <td>${vdto.gstno}</td>
            <td>${vdto.emailid}</td>
            <td>${vdto.website}</td>
            <td>${vdto.status}</td>
            <td><a href="approved?emailid=${vdto.emailid}&adminEmail=${adminDTO.emailid}"
                style="margin-bottom: 8px;margin-right: 0px"><button id="button" onclick="aproved()"
                  style="margin-top: -5px" ${ vdto.status eq "APPROVED"
                  ? 'disabled="disabled" class="bg-light text-primary"' : '' }>Approve</button></a></td>
          </tr>
       


      </table>
      </div>
       <a onclick="prevPage()" href='#' id="btn_prev" style="margin-left: 140vh"><button>Prev</button></a>
       <a onclick="nextPage()" href='#' id="btn_next"  style="margin-left: 00px"><button> Next</button></a>
   <b> page: <span id="page"></span></b> 
</div><br>


<!-- for block enable and disable -->
  <div id="id01" class="w3-modal" > 

 <!-- for block container -->
    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px;border-radius: 10px;border-bottom-left-radius:30px;">

<!-- for block animaction -->
      <div class="w3-center"><br>
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
        <!-- <img src="img_avatar4.png" alt="Avatar" style="width:30%" class="w3-circle w3-margin-top"> -->
      </div>

      <form class="w3-container" action="#" method="post">
        <div class="w3-section">
<!--         private VendorService vmanagementService; -->
         <label><b>Name</b></label> 
          <input class="w3-input w3-border w3-margin-bottom bg-light" readonly="readonly" type="text"  name="name"  id="SVname"> 	

           <label><b>Phone</b></label> 
           <input class="w3-input w3-border w3-margin-bottom bg-light" type="text" placeholder="Enter prodect Type" name="contactno" id="SVcontactno" readonly="readonly">
            
           <label><b>Service</b></label> 
           <input class="w3-input w3-border" type="text" placeholder="Enter the Prodect" name="servicetype" readonly="readonly" id="SVservicetype"><br>
            
          <label><b>GST </b></label> 
           <input class="w3-input w3-border" type="text" readonly="readonly" placeholder="Enter the required date" name="gstno"  id="SVgstno"><br>
         
          <label><b>EmailId </b></label> 
           <input class="w3-input w3-border" type="text" readonly="readonly" placeholder="Enter the total required" name="emailid" id="SVemailid"><br>
         
          <label><b>Website </b></label> 
           <input class="w3-input w3-border" type="text" readonly="readonly" placeholder="Enter the Location" name="website" id="SVwebsite" ><br>
           
           <label><b>Status </b></label> 
           <input class="w3-input w3-border" type="text" readonly="readonly" placeholder="Enter the Location" name="status" id="SVstatus" ><br>   
           
          <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">Update</button>
<!--           <input class="w3-check w3-margin-top" type="checkbox" checked="checked"> Remember me -->
        </div>
                   
      </form>
     </div>
   </div> 



<!--       for block enable and disable -->
<!--       <div id="id01" class="w3-modal"> -->

<!--         for block container -->
<!--         <div class="w3-modal-content w3-card-4 w3-animate-zoom" -->
<!--           style="max-width:600px;border-radius: 10px;border-bottom-left-radius:30px;"> -->

<!--           for block animaction -->
<!--           <div class="w3-center"><br> -->
<!--             <span onclick="document.getElementById('id01').style.display='none'" -->
<!--               class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span> -->
<!--             <img src="img_avatar4.png" alt="Avatar" style="width:30%" class="w3-circle w3-margin-top"> -->
<!--           </div> -->

<!--           <form class="w3-container" action="generateInvoice" method="post"> -->
<!--             <div class="w3-section"> -->

<!--               <input class="w3-input w3-border w3-margin-bottom bg-light" readonly="readonly" type="text" name="orderId" -->
<!--                 id="orderId"> -->
<!--               <div class="w3-input w3-border w3-margin-bottom bg-light"> -->
<!--                 <table class="table"> -->
<!--                   <tr id="invoiceRows"> -->
<!--                     <th scope="col">Order-Id</th> -->
<!--                     <th scope="col">Price</th> -->
<!--                     <th scope="col">Delivery</th> -->
<!--                     <th scope="col">discription</th> -->
<!--                     <th scope="col">Action</th> -->
<!--                   </tr> -->

<!--                 </table> -->
<!--               </div> -->
<!--           </form> -->

<!--                 <div class="w3-container w3-border-top w3-padding-16 w3-light-grey"> -->
<!--           <button onclick="document.getElementById('id01').style.display='none'" type="button" class="w3-button w3-red" -->
<!--             style="border-radius:10px;">Cancel</button> -->
<!--                   <span class="w3-right w3-padding w3-hide-small">Forgot <a href="#">password?</a></span> -->
<!--         </div> -->

<!--       </div> -->
<!--       </div> -->



      <div class="footer">
        <div>
         <!-- <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" alt="logo"
            style="height: 80px; width: 160px; margin-left: 570px;">-->
          <p style="margin-left: 750px;"><b> follow us on</b></p>

          <img style="margin-left: 700px; margin-top: -20px" alt=""
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAD30lEQVR4AayShW4cMRCGLQgzHArKDK9xor5KnyDMSZmZmZmZRWWuKMy8e3y7U8/KvXG6PlR+6dM3Wtv/tElYulnzHXI918HnvQZ7PNfgGfdvz1XQEJzFtz14B++y+crCO+D2XoG97ssw475swhwuGWSOK26T3zX24luWbZbehTxe1uS+YOiuCzGwOB+3mA2yAvcFU8cO7Mrsf30R3Lz4nfMcL+eglZyNkm3QGXZhJ0snzrOw3nE61uc4zQs4wmKOxC3PaIszZA4/I2On6zSsY8ni4P9Kx6loX/WJMCCOk5G47dCZ+r79HLtxR8LfueN45F318TDEORYiW4TJHBcvbf8QhT/TJoRiJkQME2bCJvRpJnwaM8TbuX24Q/k3wS82VR0JwRwOB8nSjF5xJgRvhgxIEtEj3kngLibHcQzc1YdDetWhICSj8iD5UW8MUsX+PoBGdNzJ/qXqQHBv5f4AVHCEBX4yp1LMG26EIJ2I+2hpFn18J8OsuQi5lXv9MxV7/ZAul35HIY0k7cCduJtV7tN95bt0sNgdt5g1sjR/GrX/+E9+j8DyI35xV7eh6q/cpftY2Q5tDwfSoVxYj5jwf5Yf4qV0j+bknXtY2bbZZxxIh9KtlkERfjaD52QFcgdSvl17xkq3zP4u3TwDSAlZAZ2pgmeZgrtZ6aZpraRrGpR0kuVZFTpXv1eBu1lpx5RW3DEFFu1kmWzSM21AiehC04wWc/uUxorbpn4XtU6CiuLWKcvZ5HVPxN7ZQkZwNytqnnxW1DwBSGETWSabnPsSkjuV4G5W2DC+hwPJyCZdL/22ngLJYt7DihonfAW1Y2BRR5bJJhtvaeL9OFoJ7mZrGiC3sHb8b6vlcdswFATRL2ZCDagAX12H+3DOOWe7A8d2fFQZlsRMXpzu6z8kVnGdPcDgDbRJ5InP/nFB3lFOTMlck1TVuW8gD8wzcRO3FeQfFvfeQUbeQU5M2K0yOGBJXlVjcmaO1HFTsernecPZTd/c3ZQ0CeTM7M+S3L0MNWbpT/a94abql7ebXDrburidECiZa5LsspZUZO+kzNI2Z31r9JPsmlx7M2namzHZG7J1vaQkp6qBff09csYN3FKS6jt5w1mPA2stos8sCb/b6zFzIPMcduOG+kzORjpurUaBtRKRuRwSsz8LqnpWQuaoV8MAu9V3VF/MG9Zi2DQXAxrwUlhSENeZA7aWwyY/+bc1tkKu/hOXxkLwZswHVLlTUlINNW0mjFnswC71W+FtGLOd+9ps+7k2qw/MtEmSrlNZB+c6z5jBrPo3nZNjzXQm1FTrjgSp6dYDakr3oFd9U+9y1lO9tTZT6gAAAABJRU5ErkJggg=="
            style="margin-left: 250px;">

          <img style="margin-top: -20px; margin-left:10px; border-radius:50px; width:35px;" alt="twiterlogo"
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAA4CAMAAACfWMssAAAAV1BMVEX///8dofIAmvEAnPH2+/4GnvI0p/O22vq93vre7/3v+P7M5vvW6vwAmPHs9v6m0/mZzfiHxfdzvfbk8v1Bq/NpufUAlfGOyPcAkfBRsPR+wfZdtPTH4/udz3kPAAABLklEQVRIie2U2ZaDIAxAScLiVnHBOm39/++c0toptAZ65nHG+4bmSkwgQuzs/HuqZvyNZjUi0nBzx5qLWpaXB9IReBQaUUxHyYkTNvEDrWCFiI4Fm9f1fZTNTPBEGdmVjIgAaIN16IF2Xx23ow+kU/tY1pEIB9YT7l6I86qaSFSG9YR91HAq5JsIvOers36dyJ3trD4WxyBUUew5XpPYdXFw+IuXVKbEeqDmhNjzHhB/bq4XAXkRq1RxzIHN9JTyfOtoW6RE+2/ITm+bGc9ot72hzYgCtgurc54oNwuL7NAITHgvD6Wa/8S6l/pQ6rRFxCc218IfFh0li0NO8He3qudYU5hthJA9XodgnCVePhrjbR/eXkU4NXlpZekdoYf0YNjJzVCVTdnmw3Z2/jjfQWEJXwOeKIcAAAAASUVORK5CYII=">

          <img style="margin-top: -20px; margin-left:10px;" alt="instalogo"
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAMAAABF0y+mAAABWVBMVEVyHVFHcEx0Fvp8Fv1yFP1/FfyLCOyfBOmtAeS7AuDHAd3UANjZAs7rD6xyFvzjANTqAMHoA7l2F/yPFfx8GPmfD/zFF/XVWujoZuLyZt3pRt/yGdHyA8j1AKTViPj/9Pr////8g9f9A7r3DZz5vO794Pf+ruj9B6uzFPv7JLv+AJr+BI/+YMP/1vD+AIThG+n9GZn/vd3/YZz+AXP+GoT/r8r+AmT/b5z+HHH+I3f+G2P/ur7+FFD+Klb+fYv/wcr+LUf+LWX+AFn+Nzr+Ohz+RT7+inP+Si7/ztD+TxL/zbv+Wy//9fH+WgL+c1X+ZyX+PUv+Bzv+VXD+cxr/6eL+aQT+eAX+nDj+gwD+gxH+fSL+rYn+jwD+iwL+XhX/3rn/1bH9ZR/+lwP+nwH+xHH8ZC3+qAH+sAD+wAP+x1P+zGb+pl79mQn+uAD9J3T+ygD9O1n8pRL9twuaEkMNAAAAc3RSTlMBAF3G///////////GW9j//9nY/1r///////////7//////8b/////////////////////////////////////////////////////////////////////////////////xf///1v////////Y/9j/2VzGSus+GgAAAeNJREFUeAFFjEWiU0EQRc/tqu74V9wZ4TBiB6wG3wpzVoQ7zHCXeELypHH+KVchJAESaEs0IUv0Zv+7fwLgSAOk9ZFghcnfDw4AAg18cyhWCKNVob9NCZjQLWVrEGhJbJGDoCBpMwCrJTETAKBWgdeRGb4dmJW56PdI/KU1K4X15N+AFsW0+3/WVm7Pa0Pmu4Fh7HcbRIjMIhr3FItUGAH4SNZKjK32YjKpK3Iv0yUY0b8g0Vs0oK+8wbeyMBAQwVeo44R1z7XWq6rqpn5jiTmAeQO3iceg9zkYzKvc71iEmMFbqCJJ4tDcKFrLjX5D/MEdq8GpIUUgd/tgGboTgiUDPDZ44SXNpr0gWRcAgqEmEPJRNVPOxYaOth0oAHdCxWoOzslH+RCvlafYMgFUHvAIHoCjz16io80BrH/j0AAcJ9KWKkhn5sRyGjsDtr/sqyrsWNO1cW9/cDNLZgHLSzVvn+wMq9qbKKwy7TiIaKTW56pzW6Oa+N1OKEQdvvl2RyOE4DEOZurd1cHNeab27tyjOPfhFlucXP04ovF97B6jTOzdJwFLaNAff7N6c8incH1VpiSR67q2VgjzUU7JgbMTiQtFKmgAsCAuczlflmVZn7iGBFc1awB/p4tqUS3LDb8OPwBrSrZIO/KHZwAAAABJRU5ErkJggg==">
          <img style="margin-top: -20px; margin-left:10px" alt="linkedinlogo"
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAB20lEQVRYhWP8//8/w0ACpgG1nYGBgQXGaNh106Jx160lDAwMyjS28269m1pMg5v6CQYGBgZGWBQwlmy+QwfL4Y743+OrwsCAGgX0shzFLhZcKjzURRnSLRUYGBgYGGYef8Cw4+ZrmrgEqwM81EUZtqdawPkBOhIMnrNP0MQRWHMBzOeExGjmAHoCrA6YefwBUWLUAFjTwI6brxk8Z58YuEQIcwSplioIcjKIcLMxvPn6i+HJxx8Mf/4RLuZxOuB/jy8Kn7FkM4b4gbtvGTxnn2Aod1JhyLFWZBDhZoPL/fj9l2HFhWcMlduuM7z4/JN0BxADeNiYGXanWzDYKApjyHGwMjMkmMoyWMgLMlhOOszw4ccfrGZQlAtMZAWwWo4MNMR4GLKtFXHKUy0bvvn6i+HBu28MX35i+jRETxKnPoqigIGBgeHPv38MqasuMiw484SBgYGBQYCDhWF9oimDg7IIXI2OJC9O/RSHwJSjD+CWMzAwMHz48Yehdc9tFDUsTLitodgBK88/xRC78+Yr0fopdgC2LPbh+2/6OeDB+++YDsCR5WjiAErBqANGHYDcLKdrF+l/jy8jA8MgCAFkB9ylo71wu+AOqHdTi6GTI+7uz7SMgHEYR3zvGABAiZoIrctOXgAAAABJRU5ErkJggg==">
          <p style="margin-left: 650px; margin-top: 0px">Copyright  2024, All Right Reserved</p>

        </div>

        <script>
//         	 function  onViewInvoiceModal() {
//             const response=getData();
//             response.then(res=>{
//               for (let i = 0; i<res.length; i++) {
//               const row=`<tr>
//                     <td>${res[i].orderId}Iggh</td>
//                     <td>${res[i].price}</td>
//                     <td>${res[i].delivarycharge}</td>
//                     <td>${res[i].discription}</td>
//                     <td><button id="button" >order</button></td>
//                   </tr>`;
                  
//                 document.getElementById("invoiceRows").insertAdjacentHTML("afterend",row);

//               }
//             })
//             document.getElementById("id01").style.display = 'block';
//           }

//           async function getData(){

//             const resp =  fetch("http://localhost:8080/PinXworkz/forView");   
//            const res= resp.then(res=>{return res.json()});
//             return res;

//           }


  /*function onEditModal(){
   			
  const resp=axios("http://localhost:8080/PinXworkz/forSearchVender/"+gstno);
   			resp.then(function (result){
    				console.log(result.data.gstno);
    				document.getElementById('SVname').value=result.data.name;
   				 document.getElementById('SVcontactno').value=result.data.contactno; 
   				 document.getElementById('SVservicetype').value=result.data.servicetype; 
   				 document.getElementById('SVgstno').value=result.data.gstno;
   				 document.getElementById('SVemailid').value=result.data.emailid;
   				document.getElementById('SVwebsite').value=result.data.website;
   				document.getElementById('SVstatus').value=result.data.status;
    				})
  	 document.getElementById('id01').style.display='block'; 
   }
  */






          function logOut() {
            alert("you are Profile is getting Logout")

          }
          
          //for pagination..
          var current_page = 1;
var records_per_page = 5;
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
          
          
        </script>


    </body>

    </html>