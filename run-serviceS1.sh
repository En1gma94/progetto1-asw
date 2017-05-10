#!/bin/bash

# Script per avviare il servizio secondario S1 

cd ProgettoASW-S1

echo Running SERVICE S1 

mvn install
cd target
java -jar ProgettoASW-S1-0.0.1-SNAPSHOT.jar


