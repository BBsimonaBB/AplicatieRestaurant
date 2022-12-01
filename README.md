# AplicatieRestaurant

This application manages a DeliveryService for a restaurant. As the pandemic hit over, restaurants all over the world had to find a way to keep a close connection to their clients. This app has the objective to represent more than just one use-case: the user of the app can be a client, an admin or an employee. This project's data is stored using the Serialization tool of Java.

## Requirements

Running the program requires an IDE for Java development.
I personally use IntelliJ IDEA Community 2021.2.3

```bash
java --version
java 11.0.12 2021-07-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.12+8-LTS-237)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.12+8-LTS-237, mixed mode)
```

## Usage
The application starts with the log in screen. Each user that logs in will be automatically redirected to the window that will show him only the actions he is allowed to do. In this way, we introduce use-case and the different scenarios for different types of users
![image](https://user-images.githubusercontent.com/69772634/205098908-33ba17a6-34b3-45fe-a5f2-91fefdaba313.png)

If the user does not have an account, a client account can be made on spot. Admin and employee accounts can not be made by usual users.
This photo shows the actions that can be done by the admin   
![image](https://user-images.githubusercontent.com/69772634/205149828-73c22f0c-8143-4204-abde-98c8846d6fc2.png)

It is important to mention that this application simulates a resturant that has over 1000 dishes. All of them are stored in a .csv file, they are then serialized and used when needed. The same serialization is user with creating new accounts or creating new products.

Also, we have basic products and Composite products - this way we follow an important Java principile in the development of this app (Composite Design).
The admin can create composite products that are automaitcally stores using serialization.

Another interesting functionality that we have is the was we can sort our product after any criteria, as seen in the photo. A major sort is done on all the criteria. The logic behind is based on lambda expressions.  
![image](https://user-images.githubusercontent.com/69772634/205150874-56baca23-f5ba-4b28-9cdc-c8d3f3fa68f4.png)

The employee sees all the new orders as notifications which he has to mark as seen/read.  
![image](https://user-images.githubusercontent.com/69772634/205150608-2f7f70af-1bba-493e-9618-d73f5d091b22.png)

## Output

We can yet again generate reports. More exactly, the admin has the right to generate reports based on some criterias that he can choose. Those reports are created as .txt files in a designed directory.

![image](https://user-images.githubusercontent.com/69772634/205151168-f01484ea-3b68-448c-bc4a-21e9ba09143b.png)



