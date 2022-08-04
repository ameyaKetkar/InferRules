###############################################
### Use comby binary distribution image     ###
###############################################
# FROM comby/comby:latest AS comby-binary-distribution

###############################################
### Build rule inference server             ###
###############################################
FROM ocaml/opam:ubuntu-20.04-ocaml-4.14

RUN sudo apt-get update && sudo apt-get install -y libev4 libpcre3-dev ca-certificates-java pkg-config libev-dev
RUN sudo apt-get install -y default-jdk


RUN curl -sL get-comby.netlify.app > install.sh
RUN chmod +x install.sh
RUN sudo ./install.sh

WORKDIR /home/InferRules

COPY build.gradle /home/InferRules  
COPY gradle /home/InferRules/gradle
COPY gradlew /home/InferRules  
COPY server /home/InferRules/server
COPY settings.gradle /home/InferRules
COPY src /home/InferRules/src

RUN ./gradlew build -x test --no-daemon
RUN tar xvf ./build/distributions/InferRules-1.0-SNAPSHOT.tar

RUN sudo chown -R $(whoami) /home/InferRules

RUN sudo apt-get install -y openssl libssl-dev

WORKDIR /home/InferRules/server
RUN opam exec -- opam install dune
RUN opam exec -- opam install . --deps-only 
RUN opam exec -- make release

EXPOSE 8080/tcp

###############################################
### Start the server                        ###
###############################################
ENTRYPOINT ["./_build/default/server.exe"]
