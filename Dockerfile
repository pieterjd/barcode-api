# First stage: JDK with GraalVM
#FROM ghcr.io/graalvm/jdk:21 AS build
FROM vegardit/graalvm-maven:latest-java21 AS build

# Update package lists and Install Maven
#RUN <<EOF
#microdnf update -y
#microdnf install -y maven gcc glibc-devel zlib-devel libstdc++-devel gcc-c++
#microdnf clean all
#EOF

WORKDIR /usr/src/app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .

RUN mvn -Pnative native:compile -DskipTests

# Second stage: Lightweight debian-slim image
FROM debian:bookworm-slim

WORKDIR /app

# Copy the native binary from the build stage
COPY --from=build /usr/src/app/target/barcode /app/barcode

# Run the application
CMD ["./barcode"]