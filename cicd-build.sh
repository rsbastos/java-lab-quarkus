#!/bin/bash -xe
APP=$1
ROOT=$(pwd)

cd "$APP"

./mvnw clean
./mvnw versions:set -DremoveSnapshot
APP_VERSION=$(./mvnw -q -Dexec.executable=echo -Dexec.args='${project.version}' --non-recursive exec:exec)
./mvnw package
./mvnw versions:set -DnextSnapshot

git add pom.xml


cd "$ROOT"

sed -i "s|dio\/${APP}:\\\${TAG:-[0-9]*.[0-9]*.[0-9]*}|dio/${APP}:\${TAG:-${APP_VERSION}}|" docker-compose.yml
git add docker-compose.yml

git commit -m "cicd: bump version ${APP}:${APP_VERSION}"

TAG=$APP_VERSION docker compose build --no-cache "$APP"

docker images "dio/${APP}"
