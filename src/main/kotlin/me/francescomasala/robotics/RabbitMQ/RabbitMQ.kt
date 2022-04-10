package me.francescomasala.robotics.RabbitMQ

import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import io.ktor.utils.io.errors.*


class RabbitMQ(RabbitMQServer: String) {
    val RabbitMQServer = RabbitMQServer

    fun send(Message: String, QueueName: String){
        val postman = ConnectionFactory()
        postman.newConnection("amqp://guest:guest@$RabbitMQServer:5672/").use { connection ->
            try {
                connection.createChannel().use {

                        channel ->
                    channel.queueDeclare(QueueName, false, false, false, null)
                    channel.basicPublish(
                        "",
                        QueueName,
                        null,
                        Message.toByteArray(Charsets.UTF_8)
                    )
                    println("[Postman] Sent message:\t\t $Message")
                }
            } catch (e: IOException) {
                println(" [!] Failed to connect")
            }
        }
    }

    fun recieve(QueueName: String){
        val postman = ConnectionFactory()
        val connection = postman.newConnection("amqp://guest:guest@$RabbitMQServer:5672/")
        val channel = connection.createChannel()
        val consumerTag = "Mailbox"

        channel.queueDeclare(QueueName, false, false, false, null)

        println("[$consumerTag] is waiting for messages:")

        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, Charsets.UTF_8)
            println("[$consumerTag] Received message:\t $message")
        }
        val cancelCallback = CancelCallback { consumerTag: String? -> println("[$consumerTag] was canceled") }
        channel.basicConsume(QueueName, true, consumerTag, deliverCallback, cancelCallback)
    }
}