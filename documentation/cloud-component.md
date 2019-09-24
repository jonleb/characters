@startuml

/'
  * Dicetower v3 is embeded in a docker
  * Docker is deployed in EC2
  * Kafka is not yet used and will be for
    different ressults
  * Must be automated
'/

cloud EC2 {
    node docker {
        artifact dicetower
        note left
              dicetower is already deployed
        end note
        artifact kafka

        dicetower .. kafka:log
    }
}

@enduml
