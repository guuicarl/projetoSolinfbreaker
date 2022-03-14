<template>
  <h1 class="text-4xl h-1 mt-5 ml-20 text-gray-900 font-mono">
    Olá <span class="text-yellow-500">{{ this.claims.name }}</span
    >, o que deseja fazer hoje?
  </h1>
  <div class="mt-24 flex">
    <!-- card vem aqui -->
    <div
      class="
        bg-white
        rounded-lg
        shadow-2x1
        w-5/12
        ml-20
        -mt-10
        flex-initial
        h-1/2
      "
    >
      <!-- header -->
      <header
        class="
          bg-gray-800
          rounded-t-lg
          py-3
          px-8
          text-xl
          font-extrabold
          text-gray-100
          flex
          justify-between
        "
      >
        COTAÇÕES
        <div class="flex flex-col">
        <a href="/about" class="text-xs hover:text-gray-300">Ver grafico de alterações</a>
        <a href="/stocks" class="text-xs hover:text-gray-300">Listar todas</a>
        </div>
      </header>
      <div class="p-5">
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Stock
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Bid min
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Bid Max
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Ask min
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Ask Max
              </th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="stock in stocks" :key="stock">
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <div class="ml-4">
                    <div
                      class="text-sm font-medium text-gray-900"
                      v-if="this.$root.authenticated"
                    >
                      {{ stock.stock_name }}
                    </div>
                  </div>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div
                  class="text-sm text-gray-900"
                  v-if="this.$root.authenticated"
                >
                  {{
                    stock.bid_min.toLocaleString("pt-br", {
                      style: "currency",
                      currency: "BRL",
                    })
                  }}
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="text-sm text-gray-900">
                  {{
                    stock.bid_max.toLocaleString("pt-br", {
                      style: "currency",
                      currency: "BRL",
                    })
                  }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{
                  stock.ask_min.toLocaleString("pt-br", {
                    style: "currency",
                    currency: "BRL",
                  })
                }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{
                  stock.ask_max.toLocaleString("pt-br", {
                    style: "currency",
                    currency: "BRL",
                  })
                }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="bg-white rounded-lg shadow-2x1 w-5/12 ml-20 -mt-10 h-1/2">
      <!-- header -->
      <header
        class="
          bg-gray-800
          rounded-t-lg
          py-3
          px-8
          text-xl
          font-extrabold
          text-gray-100
        "
      >
        ULTIMAS TRANSAÇÕES DO USUÁRIO
      </header>
      <div class="p-5">
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Stock
              </th>
              <th
                scope="col"
                class="
                  px-2
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Volume
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Price
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Type
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Status
              </th>
              <th
                scope="col"
                class="
                  px-3
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Abrir/Fechar ordem
              </th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="order in orders.content" :key="order">
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <div class="ml-4">
                    <div
                      class="text-sm font-medium text-gray-900"
                      v-if="this.$root.authenticated"
                    >
                      {{ order.stock_name }}
                    </div>
                  </div>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div
                  class="text-sm text-gray-900"
                  v-if="this.$root.authenticated"
                >
                  {{ order.volume }}
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="text-sm text-gray-900">
                  {{
                    order.price.toLocaleString("pt-br", {
                      style: "currency",
                      currency: "BRL",
                    })
                  }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500" v-if="order.type ==0">
                Compra
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500" v-if="order.type ==1">
                Venda
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500" v-if="order.status == 1">
                Aberto
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500" v-if="order.status == 2">
                Fechado
              </td>
              <td v-if="order.status == 1" class="px-6 py-4 whitespace-nowrap text-sm text-gray-500 flex">
                <button>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  class="h-5 w-5 text-red-600"
                  viewBox="0 0 20 20"
                  fill="currentColor"
                  @click="fechar(order.id)"
                >
                  <path
                    fill-rule="evenodd"
                    d="M5 9V7a5 5 0 0110 0v2a2 2 0 012 2v5a2 2 0 01-2 2H5a2 2 0 01-2-2v-5a2 2 0 012-2zm8-2v2H7V7a3 3 0 016 0z"
                    clip-rule="evenodd"
                  />
                </svg>
                </button>
                Fechar
              </td>
              <td v-if="order.status ==2 " class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                <h1>Ordem finalizada!</h1>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
          <div class="flex flex-col items-center">
  <!-- Help text -->
  <span class="text-sm text-gray-700 dark:text-gray-400">
      Showing <span class="font-semibold text-gray-900 dark:text-white">{{this.orders.number + 1}}</span> page of <span class="font-semibold text-gray-900 dark:text-white">{{this.orders.totalPages}}</span> Pages
  </span>
  <!-- Buttons -->
  <div class="inline-flex mt-2 xs:mt-0">
      <button v-if="this.page != 0 " @click="prev" class="py-2 px-4 text-sm font-medium text-white bg-gray-800 rounded-l hover:bg-gray-900 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
          Prev
      </button>
      <button v-if="this.page < this.orders.totalPages - 1" @click="next" class="py-2 px-4 text-sm font-medium text-white bg-gray-800 rounded-r border-0 border-l border-gray-700 hover:bg-gray-900 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
          Next
      </button>
  </div>
</div>
    </div>
    
  </div>



</template>

<script>
import "../assets/tailwind.css";
import axios from "axios";
import { fetchEventSource } from "@microsoft/fetch-event-source";

export default {
  name: "home",
  data: function () {
    return {
      claims: "",
      page: 0,
      stocksName: [],
      status: true,
      id: 0,
      welcome: "",
      users: [],
      stocks: [],
      orders: [],
      money: {
        decimal: ",",
        thousands: ".",
        prefix: "R$ ",
        precision: 0,
        masked: false /* doesn't work with directive */,
      },
    };
  },
  created() {
    this.user();
    this.setup();
    this.novo();
  },
  methods: {
    async next (){
      this.page = this.page + 1
      this.user()
    },
    async prev (){
      this.page = this.page - 1
      this.user()
    },
    async novo() {
      const nova = (stock) => {
        this.stocks = stock;
      };
      let accessToken = this.$auth.getAccessToken();
      await fetchEventSource("http://localhost:8085/temporeal", {
        headers: {
          Authorization: "Bearer " + accessToken,
        },
        onmessage(ev) {
          console.log(ev);
          console.log(JSON.parse(ev.data));
          nova(JSON.parse(ev.data));
        },
        onerror(err) {
          if (err) {
            console.log("Sou do erro", err);
            throw err; // rethrow to stop the operation
          }
        },
      });
    },
    async setup() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        console.log(`Authorization: Bearer ${accessToken}`);
        console.log("testando");
        console.log(this.claims.name);
        try {
          let response = await axios.get("http://localhost:8082/", {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.welcome = response.data;
        } catch (error) {
          this.welcome = `${error}`;
        }
        try {
          let response = await axios.get("http://localhost:8085/updated", {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.stocks = response.data;
          console.log(this.stocks);
        } catch (error) {
          this.stocks = `${error}`;
        }
      }
    },
    async user() {
      this.claims = await this.$auth.getUser();
      let accessToken = this.$auth.getAccessToken();
      try {
        let response = await axios.get(
          `http://localhost:8082/u/${this.claims.name}`,

          {
            headers: { Authorization: "Bearer " + accessToken },
          }
        );
        this.id = response.data;
      } catch (error) {
        this.users = `${error}`;
      }
      try {
        let response = await axios.get(`http://localhost:8082/uo/${this.id}?pageSize=5&pageNumber=${this.page}`, {
          headers: { Authorization: "Bearer " + accessToken },
        });
        this.orders = response.data;
        console.log(this.orders)
        console.log((this.page + 1))
      } catch (error) {
        this.orders = `${error}`;
      }
    },
    async fechar(id){
      let accessToken = this.$auth.getAccessToken();
        await axios.patch(`http://localhost:8082/alterar/${id}`, {status: 2},{
          headers: { Authorization: "Bearer " + accessToken },
        });
        this.user()
    },
    async abrir(id){
      let accessToken = this.$auth.getAccessToken();
        await axios.patch(`http://localhost:8082/alterar/${id}`, {status: 1},{
          headers: { Authorization: "Bearer " + accessToken },
        });
        this.user()
    },
  },
};
</script>