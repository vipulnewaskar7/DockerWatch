
<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="DockerWatchUI/src/assets/image/DWlogo.png" alt="Logo" width="80" >
  </a>

  <h3 align="center">DockerWatch</h3>

  <p align="center">
    An project to watch over docker host.
    <br />
    <br />
    <a href="https://github.com/vipulnewaskar7/DockerWatch/issues">Report Bug</a>
    ·
    <a href="https://github.com/vipulnewaskar7/DockerWatch/issues">Request Feature</a>
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

The DockerWatch project is provide the convinence way to watch over multiple Docker hosts. Projcet to watch over docker host container and live logs -- I think this is it.

Gives visual representations of containers/images on the host system. DockerWatch will start running on port 8080. Currently, it shows info about docker engine running on a local machine. 

Here's why:
* View Container and image details of docker.
* Connect with multiple remote docker host.
* Perform some operations on docker host.

A list of commonly used resources that I find helpful are listed in the acknowledgements.

### Built With

This section should list any major frameworks that you built your project using. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.
* [Bootstrap](https://getbootstrap.com)
* [JQuery](https://jquery.com)
* [Angular](https://angular.io)
* [Spring Boot](https://spring.io)


<!-- GETTING STARTED -->
## Getting Started

This is an example of how do you setting up your project locally. To get a local copy up and running follow these simple example steps.

### Prerequisites

To Get this project up and running you need to install following tools / software on your system.

* [Docker](https://www.docker.com)
* [Java](https://openjdk.java.net)
* [Maven](https://maven.apache.org)
* [Node.js](https://nodejs.org)
  

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/vipulnewaskar7/DockerWatch.git
   ```

#### DockerWatch Spring Server  

1. From project root directory, i.e. DockerWatch, run :
   ```sh
   mvn clean
   ```
2. Install maven dependancies
   ```sh
   mvn install
   ```
3. Start your `spring-boot` application
   ```sh
   mvn exec:java
   ```
  
#### DockerWatch UI  
  
1. Change Directory and Install NPM packages
   ```sh
   cd DockerWatchUI
   npm install
   ```
2. Start your `angular` application
   ```sh
   npm run start
   ```

#### Docker Setup  

1. Open Docker Desktop and follow instruction
   ```sh
   Docker > Settings > General
   ```
2. `enable` The folowing checkbox to expose the docker API on localhost
   ```sh
   Expose daemon on tcp://localhost:2375 without TLS
   ```
