# Stuff Box

Stuff Box é o local aonde voce pode ter acesso, configurar, monitorar e interagir com seus dispositivos IOT.

Stuff box android é uma aplicação android para integração entre dispositivos IOT e um servidor MQTT oferecendo uma interface de interação com o usuario. Stuff box é desenhado para que seja possivel integrar qualquer dispositivo IOT a sua interface, para tal o aplicativo deve conhecer os topicos que determinada coisa escreve ou publica, bem como o endereço e credenciais para acesso do servidor MQTT.

A tela inicial do aplicativo é uma lista com todos os dispositivos adicionados até então e um botão para adicionar novos módulos ao sistema.

![alt text](https://github.com/AndreyViktor/SmartHome-Android/blob/master/Lista%20de%20Modulos.jpeg)

é possivel controlar cada módulo individualmente através da lista ou acessar configurações especificas.

-Luminaria LED WRGB:
O primeiro Módulo consiste em um controle PWM para um led WRGB, o usuario escolhe uma cor no aplicativo e pode facilmente controlar a intensidade de luz.

![alt text](https://github.com/AndreyViktor/SmartHome-Android/blob/master/RGBConfigActivity.jpeg)

o primeiro slider controla a luminosidade do led branco apenas, para dar mais vivacidade as cores ou mais luminosidade ao ambiente.
através do color picker é possivel escolher uma cor e facilmente escolher o branco puro caso queira maximo de luminosidade.
segundo slider controla a intensidade do PWM sobre todos os leds, mantendo as proporções de cores.
