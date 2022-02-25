<template>
    <h1 class="text-4xl h-1 mt-5 ml-20 text-gray-900 font-mono">Olá <span class="text-yellow-500">{{this.claims.name}}</span>, o que deseja fazer hoje?</h1>
  <div class="py-40 flex ">
    <!-- card vem aqui -->
    <div class="bg-white rounded-lg shadow-2x1 w-5/12 ml-20 -mt-10 flex-initial h-1/2">
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
        COTAÇÕES
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
                  {{ stock.bid_min.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}) }}
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="text-sm text-gray-900" >
                  {{ stock.bid_max.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})}}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ stock.ask_min.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}) }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ stock.ask_max.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}) }}
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
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="order in orders" :key="order">
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
                  {{ order.price.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}) }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ order.type }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ order.status }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import "../assets/tailwind.css";
import axios from "axios";
import { fetchEventSource } from "@microsoft/fetch-event-source";
const people = [
  {
    name: "Jane Cooper",
    title: "Regional Paradigm Technician",
    department: "Optimization",
    role: "Admin",
    email: "jane.cooper@example.com",
    image:
      "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=4&w=256&h=256&q=60",
  },
  // More people...
];

export default {
  name: "home",
  data: function () {
    return {
      claims: "",
      id: 0,
      welcome: "",
      users: [],
      stocks: [],
      orders: [],
      money: {
          decimal: ',',
          thousands: '.',
          prefix: 'R$ ',
          precision: 0,
          masked: false /* doesn't work with directive */
        }
    };
  },
  created() {
    this.user();
    this.setup();
    this.novo();
  },
  methods: {
    async novo (){
      const nova = (stock) => {
        this.stocks = stock
      }
            let accessToken = this.$auth.getAccessToken();
      await fetchEventSource("http://localhost:8085/temporeal", {
        headers: {
          Authorization: "Bearer " + accessToken,
        },
        onmessage(ev) {
            console.log(ev)
            console.log(JSON.parse(ev.data))
            nova(JSON.parse(ev.data))
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
        console.log(this.claims.name)
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
          console.log();
        } catch (error) {
          this.stocks = `${error}`;
        }
        for (var i = 0; i < this.orders.length; i++) {
          if (this.orders[i].status == 1) {
            this.orders[i].status = "Aberto";
          } else {
            this.orders[i].status = "Fechado";
          }
          if(this.orders[i].type == 0){
            this.orders[i].type = "Compra"
          } else{
            this.orders[i].type = "Venda"
          }
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
          let response = await axios.get(`http://localhost:8082/uo/${this.id}`, {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.orders = response.data;
        } catch (error) {
          this.orders = `${error}`;
        }
    },
  },
  setup() {
    return {
      people,
    };
  },
};
</script>