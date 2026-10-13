<!DOCTYPE html>

<html>

	<head>
	
		<link href="StyleSheet.css" rel="StyleSheet"> 

	</head>
	
	<body>
	
	<div id='header'>
	
		<?php include "Header.php"; ?>
	
	</div>
	
	<div id="menu" >
	
		<?php include "Menu.php"; ?>
	
	</div>
	
	<div id="content" >
	<?php 
	
	interface employee {
	    public function  displayEmployeeInfo();
	}
	
	class management implements employee {
	    
	   protected $sin;
	   protected $age;
	   protected $salary;
	   
	   public function __construct($sin, $age, $salary){
	       $this -> sin = $sin;
	       $this -> age = $age;
	       $this -> salary = $salary;
	   }
	   
	   public function displayEmployeeInfo(){
	       
	       echo "Sin : {$this -> sin} <br>";
	       echo "Age : {$this -> age} <br>";
	       echo "Salary : {$this -> salary} <br>";
	       
	       
	   }
	}
	   
	   
	   /* https://www.php.net/manual/en/language.oop5.decon.php */
	   class manager extends management {
	       
	       private $adminLevel;
	       
	       public function __construct($sin, $age, $salary, $adminLevel){
	           parent::__construct($sin, $age, $salary);
	           $this->adminLevel = $adminLevel;
	       }
	       
	       public function displayEmployeeInfo(){
	           parent::displayEmployeeInfo();
	           echo "Admin Level: {$this->adminLevel} <br>";
	       }
	       
	       
	   }
	   
	   class Development implements Employee {
	       
	       protected $sin;
	       protected $age;
	       protected $salary;
	       
	       public function __construct($sin, $age, $salary) {
	           $this->sin = $sin;
	           $this->age = $age;
	           $this->salary = $salary;
	       }
	       
	       public function displayEmployeeInfo() {
	           echo "Sin : {$this->sin} <br>";
	           echo "Age : {$this->age} <br>";
	           echo "Salary : {$this->salary} <br>";
	       }
	   }
	   
	   
	   class ITSpecialist extends Development {
	       
	       private $projectAssigned;
	       
	       public function __construct($sin, $age, $salary, $projectAssigned) {
	           parent::__construct($sin, $age, $salary);
	           $this->projectAssigned = $projectAssigned;
	       }
	       
	       public function displayEmployeeInfo() {
	           parent::displayEmployeeInfo();
	           echo "Project Assigned: {$this->projectAssigned} <br>";
	       }
	   
	   }
	   
	   $managerFirst = new manager(123456789,45,110000,"MG-06");
	   $managerSeocnd = new manager(987654321,55,120000,"MG-07");

	   $managerFirst->displayEmployeeInfo();
	   echo "<br>";
	   $managerSeocnd->displayEmployeeInfo();
	   
	   $itFirst = new ITSpecialist(567451234,35,100000,"T1SR");
	   $itSecond = new ITSpecialist(2344511234,30,90000,"HIMS");
	   
	   echo "<br>";
	   $itFirst->displayEmployeeInfo();
	   echo "<br>";
	   $itSecond->displayEmployeeInfo();
	
	
	?>
	
	</div>
			
	<div id="footer" >
	
	<?php include "Footer.php"; ?>
	
	</div>
			
			
	</body>

</html>