Hola soy chasqui-server


Los datos para el import se encuentran en "src/main/resources/static"
- para el import en bulk se ha ocupado el "products_bulk_data.txt"
  
el comando para importar en elasticsearch es el siguiente


curl -s -H "Content-Type: application/x-ndjson" -XPOST http://localhost:9200/_bulk --data-binary "src/main/resources/static/products_bulk_data.txt"





Pasos a seguir

1. levantar elasticsearch.
    - se recomienda hacer uso del comando 'docker compose up'
2. Instalar el plugin de phonetics analisys, desde la consola de la imagen de elasticsearch ejecutar el siguiente comando
- bin/elasticsearch-plugin install analysis-phonetic

3. crear el index llamado products 

```json
PUT products
{
  "settings": {
    "index": {
      "analysis": {
        "analyzer":{
          "sound_analyzer":{
            "tokenizer": "standard",
            "filter":[
              "lowercase",
              "sound_filter"
            ]
          }
        },
        "filter": {
          "sound_filter":{
            "type":"phonetic",
            "encoder":"double_metaphone"
          }
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "title": {
        "type": "text",
        "analyzer": "sound_analyzer"
      }
    }
  }
}
```

4. hacer el import en bulk, con la descripcion de arriba
5. Levantar el servidor de sprint boot
6. ejecutar las llamadas desde postman
    GET
    - http://localhost:8080/api/product/?productName=champu

//Ejemplo llamada desde la terminal
```bash
curl -s -H "Content-Type: application/x-ndjson" -XGET http://localhost:8080/api/product/?productName=champu
```

el resultado de la llamada al servicio rest tendria que ser el siguiente
```json
[
  {
    "title": "Shampoo",
    "type": "higiene personal"
  },
  {
    "title": "Shampoo colorante",
    "type": "higiene personal"
  },
  {
    "title": "Shampoo con acondicionador",
    "type": "higiene personal"
  },
  {
    "title": "Shampoo anticaspa/anticaída",
    "type": "higiene personal"
  },
  {
    "title": "Shampoo p/bebés y niños",
    "type": "higiene personal"
  },
  {
    "title": "Shampoo con acondicionador p/bebés y niños",
    "type": "higiene personal"
  },
  {
    "title": "Shower gel, shampoo p/ducha, jabón líquido",
    "type": "higiene personal"
  },
  {
    "title": "Shower gel, shampoo p/ducha, jabón líquido p/niños",
    "type": "higiene personal"
  }
]
```



Referencias

- https://www.elastic.co/guide/en/elasticsearch/plugins/current/analysis-phonetic.html
- 