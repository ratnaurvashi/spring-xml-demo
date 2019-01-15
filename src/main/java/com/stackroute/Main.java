package com.stackroute;

import com.stackroute.domain.Movie;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {
    public static void main(String[] args){

        //Using XmlBeanFactory
        BeanFactory beanFactory=new XmlBeanFactory(new ClassPathResource("beans.xml"));
        Movie movie1=(Movie) beanFactory.getBean("movie1");
        System.out.println("Using XmlBeanFactory: "+ movie1.getActor());

        //Using Spring 3.2 BeanDefinitionRegistry and BeanDefinitionReader
        BeanDefinitionRegistry beanDefinitionRegistry=new DefaultListableBeanFactory();
        BeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(beanDefinitionRegistry);
        beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        Movie movie2=(Movie)((DefaultListableBeanFactory) beanDefinitionRegistry).getBean("movie1");
        System.out.println("Using Spring 3.2 BeanDefinitionRegistry and BeanDefinitionReader: "+movie2.getActor());

        //Using ApplicationContext
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        Movie movie3=(Movie) context.getBean("movie1");
        System.out.println("Using ApplicationContext: "+ movie3.getActor());

        Movie movie4=(Movie) context.getBean("movie2");
        System.out.println(movie4.getActor());

        Movie movie5=(Movie) context.getBean("movie3");
        System.out.println(movie5.getActor());
    }
}
