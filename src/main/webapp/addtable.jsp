<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Table</title>
<link rel="stylesheet" href="css/addtbl.css">
</head>
<body>
<div class="container">
  
  <form action="AddTableAction">
    <ul class="flex-outer">
      <li>
        <label for="first-name">Item Name</label>
        <input type="text"  name="itemname" placeholder="Enter your Item name here">
      </li>
      <li>
        <label for="last-name">Item Quantity</label>
        <input type="text"  name="itemquant" placeholder="Enter No. of Quantities Available">
      </li>
      <li>
        <label for="email">Item Location</label>
        <input type="text"  name="itemloc" placeholder="Where is Item at">
      </li>
      
       <li>
        <label for="email">Item Status</label>
        <input type="text"   name="itemstat" placeholder="Enter Item Status">
      </li>
        
      <li>
        <button type="submit">Submit</button>
      </li>
    </ul>
  </form>
</div>
 

</body>
</html>