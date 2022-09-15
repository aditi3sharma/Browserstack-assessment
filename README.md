# Browserstack Assignment


############  How to Setup the Project  ##############

- git clone https://github.com/aditi3sharma/Browserstack-assessment.git
It will create folder "Browserstack-assessment" on local machine
"Browserstack-assessment"  contains two projects : 

1. assignment  - local project tested on single browser
2. browserstack-assignment  - project created using 3 parallels provided by browser stack + two  self      configured paralles

Import Maven project ->  import both projects available in "Browserstack-assessment" folder.

#############  How To Run Browserstack Assignment (testng-browserstack)  ##############

1.  Install Dependencies
- Run as -> Run configuration -> Select maven builds from left menu and double click on it 
- In new configuration select project from workspace -> In goals add "compile" -> apply -> run 

2. Run Project 
- Run as -> Run configuration -> Select maven builds from left menu and double click on it 
- In new configuration select project from workspace -> In goals add "test -P parallel" apply -> run 

Project will run and also you will see the mobile list in the Eclipse console

############ How To Run local assignment  #############

- Do Maven install -> Run the project 
- Make sure you add the correct path of chromium


############ How To change the credential of Browserstack Test account ################

- Login in "https://automate.browserstack.com"
- You will see Access Key on navigation bar click on it dropdown will open 
-  Copy the username and Access key from there 
- In Browserstack Assignment (testng-browserstack) open below file: 
     Browserstack-assessment/browserstack-assignment/src/test/resources/conf/parallel.conf.json 
- Replace the username and access key