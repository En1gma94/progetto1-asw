#!/bin/bash

# Script per avviare il servizio secondario S2 

cd ProgettoASW-S2

echo Running SERVICE S2

mvn install
cd target
java -jar ProgettoASW-S2-0.0.1-SNAPSHOT.jar


