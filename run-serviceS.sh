#!/bin/bash

# Script per avviare il servizio principale S 

cd ProgettoASW-S

echo Running SERVICE S 

mvn install
cd target
java -jar ProgettoASW-S-0.0.1-SNAPSHOT.jar


