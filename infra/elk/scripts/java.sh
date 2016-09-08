#!/bin/bash -eux

set -e

yum install -y java-1.8.0-openjdk-devel

echo "Check installation"
java -version
javac -version
