FROM java

RUN echo "executing 'useradd -d /home/skysail -m -s /bin/bash skysail'"
RUN useradd -d /home/skysail -m -s /bin/bash skysail
#RUN echo skysail:skysail | chpasswd
#RUN echo 'skysail ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers.d/skysail
#RUN chmod 0440 /etc/sudoers.d/skysail
USER skysail
ENV HOME /home/skysail
WORKDIR /home/skysail

RUN echo "creating /home/skysail/products/demo"
RUN mkdir -p /home/skysail/products/demo

RUN echo "copying generated/skysail.app.demo.jar /home/skysail/products/demo/skysail.app.demo.jar"
COPY generated/skysail.app.demo.jar /home/skysail/products/demo/skysail.app.demo.jar
COPY etc/docker/start.sh /home/skysail/products/demo/start.sh
#RUN chmod 775 /home/skysail/products/demo/start.sh

WORKDIR /home/skysail/products/demo

#CMD ["ls","-al"]
#CMD ["start.sh", "run"]
#CMD ["java", "-jar" "skysail.app.demo.jar"]
CMD ["java","-jar","/home/skysail/products/demo/skysail.app.demo.jar"]