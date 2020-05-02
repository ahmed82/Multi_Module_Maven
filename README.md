
## Multi Module Maven Project.

### 1. Backend (Java J2EE JEE / Spring Boot Framework)
### 2. Frontend (Vue JS Cli 3)

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
```
mvn --projects backend spring-boot:run
```
Need to use 
https://travis-ci.org/

## Deployed in heroku
using heroku Cli
```
heroku create
```

https://quiet-island-29743.herokuapp.com/ | https://git.heroku.com/quiet-island-29743.git

Rename the Auto created app by :
```
heroku apps:rename --app quiet-island-29743 heroku-spring-vuejs
```

