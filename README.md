# Hourglass

## How to build

To build Hourglass, run the following commands:

```bash
git clone https://github.com/tomidelucca/hourglass.git
cd hourglass
mvn clean package
java -jar target/hourglass-1.0-SNAPSHOT.jar 
```

## How to run

```bash
java -jar target/hourglass-1.0-SNAPSHOT.jar [parameters]
```

## Supported parameters

| **Key** |      **Reference**      |
|:---:|:-------------------:|
|  -R |  Semisphere radius  |
|  -r |   Particle radius   |
|  -m |    Particle mass    |
|  -a |      Hole size      |
|  -n |    Max particles    |
|  -t | Simulation duration |
|  -i |         Runs        |

## Credits
© Raptor/2, 2017.


