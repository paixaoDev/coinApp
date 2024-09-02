### Projeto
Aplicativo para consultar a [coinapi.io](https://docs.coinapi.io/?shell#list-all-exchanges-get) que tras as exchanges em forma de lista.

### Credencial da API
Foi criada uma [API_KEY](https://www.coinapi.io/get-free-api-key?product_id=market-data-api) para utilizar a API.


### Features
-   **Tela de listagem:**
    - Exibir, pelo menos, os campos: `name`, `exchange_id` e `volume_1day_usd`
    - Ao tocar em um item deve ser exibida a tela de detalhes.
-   **Tela de detalhe:**
    - Exibe os detalhes da exchange.
 
#### Arquitetura
Foi utilizada MVVM + Clean ARC em Compose
