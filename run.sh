#!/bin/bash

# Script per avviare il servizio principale S e i servizi secondari S1 e S2 

echo Cleaning ALL SERVICE 

./clean-serviceS.sh & ./clean-serviceS1.sh & ./clean-serviceS2.sh

echo Running ALL SERVICE 

./run-serviceS.sh & ./run-serviceS1.sh & ./run-serviceS2.sh 
