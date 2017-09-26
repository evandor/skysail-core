# http://paulbakker.io/docker/docker-osgi/
#
# In project root dir:
#
# ./gradlew clean build skysail.core:export.core.int skysail.core:runnable
#
# cd skysail.core/etc/release
#
# docker build -t evandor/skysail-server .
#
# docker rmi $(docker images -f "dangling=true" -q)
# docker stop $(docker ps -a -q)
# docker container prune
# docker run -ti --rm -p 9102:9102 evandor/skysail-server
# docker login
# docker push evandor/skysail-server
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
RUN mkdir -p /home/skysail/products/demo/plugins

COPY core.int.jar /home/skysail/products/demo/core.int.jar
COPY config/ /home/skysail/products/demo/config/

#COPY skysail.app.demo/generated/skysail.app.demo.jar /home/skysail/products/demo/plugins/skysail.app.demo.jar

#COPY etc/docker/start.sh /home/skysail/products/demo/start.sh
#RUN chmod 775 /home/skysail/products/demo/start.sh

WORKDIR /home/skysail/products/demo

CMD ["java","-jar","/home/skysail/products/demo/core.int.jar"]

EXPOSE 9102