# Tic Tac Toe MVC by William Stanton
A Web Application that allows end-users to play a fully customizable and fun game of Tic Tac Toe, without the need of ANY Javascript.
## No JS.. HOW? WHY?
Javascript applications typically utilize Client Side Rendering (CSR). Instead, a Model View Controller (MVC) design pattern is implemented with PLAIN HTML/CSS and utilizes Server Side Rendering (SSR).

### Advantages
1. Smaller overhead, positively boosting performance of the end-user on slower devices.
2. Increased backend security
3. Larger browser compatibility (yes you.. Internet Explorer)

### Downfalls
1. More bandwidth is utilized when communicating with the server due to page refreshes when the game is updated
2. Backend server utilizes more resources

## Running Web App
To startup the Web App (on port 90), simply run the following in the project directory: ```mvn spring-boot:run```
