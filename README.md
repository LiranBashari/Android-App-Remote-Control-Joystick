# Android App Remote Control Joystick:

## Explanation of the project:
Our application is a simple joystick control application.<br />
The application connects to the flightGear simulator using IP address and port.<br />
When we were able to connect successfully, the user will be able to fly the  airplane by himself using the joystick and the seekBars on the screen.

## App content:
We organized the application with the design pattern of MVVM.<br />
model- responsible for the communication with the flight simulator.<br />
view- Responsible for the entire display to the screen.<br />
viewModel- responsible to connect between the model and the view (DataBinding). <br />


## Instructions for using the application:

* Download 'FlightGear' 2020.3.6 application.
* Open the app and go to setting and at the bottom of the page under the heading "Additional Setting" write: 
"telnet=socket,in,10,127.0.0.1,6400,tcp" and then press Fly.
* Download Android studio version 4.2.1
* Create an Android emulator. 
* Then open Android studio and go to File->open go to the project folder and click on the ‘Android-App-Remote-Control-Joystick’ with the  icon, and then press ok, and then you can enter the ip address and port 6400, and press "connect" anf then you can control the airplane by the joystick and the seekBars.

Link to demo video [here](https://youtu.be/bvnWKYNtO9I)

## Authors:
* Liran Bashari
* Doriel Fay
