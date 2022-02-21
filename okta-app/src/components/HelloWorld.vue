<template>
  <div class="py-40 flex w-full md:w-auto">
    <!-- card vem aqui -->
    <div class="bg-white rounded-lg shadow-2x1 w-5/12 ml-20">
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
                  {{ stock.bid_min }}
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="text-sm text-gray-900">
                  {{ stock.bid_max }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ stock.ask_min }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ stock.ask_max }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="bg-white rounded-lg shadow-2x1 w-5/12 ml-20">
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
                  px-6
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
                  {{ order.price }}
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
  },
  methods: {
    async setup() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        console.log(`Authorization: Bearer ${accessToken}`);
        console.log("testando");
        console.log(this.users)
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
        try {
          var id = 0;
          for(var k = 0; k < this.users.length;k++){
            if(this.claims.name == this.users[k].username){
              id = this.users[k].id
              console.log('aqui eu passo' + this.users[k].id)
            }
          }
          let response = await axios.get(`http://localhost:8082/uo/${id}`, {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.orders = response.data;
        } catch (error) {
          this.orders = `${error}`;
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
      var teste = false;
      this.claims = await Object.entries(await this.$auth.getUser()).map(
        (entry) => ({ claim: entry[0], value: entry[1] })
      );
      let accessToken = this.$auth.getAccessToken();
      try {
        let response = await axios.get(
          `http://localhost:8082/users`,

          {
            headers: { Authorization: "Bearer " + accessToken },
          }
        );
        this.users = response.data;
        console.log("olha pra baixo");
        console.log(this.users);
      } catch (error) {
        this.users = `${error}`;
      }
      for (var i = 0; i < this.users.length; i++) {
        if (this.claims[1].value == this.users[i].username) {
          teste = true;
        } else {
          console.log("Usurio ta ai besta");
        }
      }
      if (teste == false) {
        try {
          await axios.post(
            "http://localhost:8082/users",
            {
              username: this.claims[1].value,
              password: "teste1234",
              dollar_balance: 10000,
              enabled: true,
            },
            {
              headers: { Authorization: "Bearer " + accessToken },
            }
          );
        } catch (error) {
          console.log(error);
        }
        console.log(this.claims);
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