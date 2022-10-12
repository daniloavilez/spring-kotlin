package br.com.avilez.springkotlin.configuration

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.IntegerSerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class CustomKafkaTemplate {

    @Bean
    fun kafkaTemplate(): KafkaTemplate<Int, String> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun producerFactory(): ProducerFactory<Int, String> {
        return DefaultKafkaProducerFactory(producerConfigs())
    }

    private fun producerConfigs(): MutableMap<String, Any> {
        val properties = hashMapOf<String, Any>()
        properties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        properties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = IntegerSerializer::class.java
        properties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java

        return properties
    }
}