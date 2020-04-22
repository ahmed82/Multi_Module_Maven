
## Multi Module Maven Project.

### Backend Java J2EE JEE using Spring Boot Framework
### Frontend Vue JS Cli 3

## Project setup

```
spring-boot-vuejs
├─┬ backend     → backend module with Spring Boot code
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module with Vue.js code
│ ├── src
│ └── pom.xml
└── pom.xml     → Maven parent pom managing both modules
```


#### Frontend maven plugin 
ref https://github.com/eirslett/frontend-maven-plugin
### Test the frontend only:
[cd into frontend directory and run CMD]
```
$ npm run serve
```

## Run the application:
on the application root:
```
$ mvn clean install
```

Run our complete Spring Boot App:
[mvn --projects backend spring-boot:run]

C:\Users\1426391\AppData\Local\Yarn\bin