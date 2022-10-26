var amenityApi = Vue.resource('/amenity{/id}')

function getIndex(list, id){
    for (var i = 0; i < list.length; i++){
        if (list[i].id === id){
            return i;
        }
    }
    return -1;
}

Vue.component('amenity-form', {
    props: ['amenities', 'amenity'],
    data: function(){
        return {
            text: '',
            price: ''
        }
    },
    watch: {
        amenity: function(newVal, oldVal){
            this.text = newVal.amenity;
            this.id = newVal.id;
        }
    },
    template:
        '<div>'+
        '<input type="text" placeholder="write something" v-model="text"/>' +
        '<input type="number" placeholder="input price" v-model="price"/>' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods:{
        save: function(){
            var amenity = { amenity: this.text, price: this.price };

            if (this.id) {
                amenityApi.update({id:this.id}, amenity).then( result =>
                    result.json().then(data=>{
                        var index = getIndex(this.amenities, data.id);
                        this.amenities.splice(index, 1, data);
                        this.text = '';
                        this.price = '';
                        this.id = '';
                    })
                )
            } else {
                amenityApi.save({}, amenity).then(result =>
                    result.json().then(data=> {
                        this.amenities.push(data);
                        this.text = '';
                        this.price = '';
                    })
                )
            }
        }
    }
});



Vue.component('amenity-row', {
  props:['amenity', 'editMethod', 'amenities'],
  template: '<div>'+
          '<i>({{ amenity.id }})</i> {{amenity.amenity}} {{amenity.price}}'+

          '<span style="position:absolute; right:0">'+
            '<input type="button" value="Edit" @click="edit" />' +
            '<input type="button" value="X" @click="del" />' +
          '</span>'+
      '</div>',
  methods: {
    edit: function(){
        this.editMethod(this.amenity)
    },
    del: function(){
        amenityApi.remove({id: this.amenity.id}).then(result => {
            if (result.ok){
                this.amenities.splice(getIndex(this.amenities, this.amenity.id), 1)
            }
        })
    }
  }

});

Vue.component('amenities-list', {
  props:['amenities'],
    data: function(){
        return {
            amenity: null
        }
    },
  template:
  '<div style="position: relative; width:300px">'+
    '<amenity-form :amenities="amenities" :amenity="amenity" />'+
    '<amenity-row v-for="amenity in amenities" :key="amenity.id" :amenity="amenity"'+
    ':editMethod="editMethod" :amenities="amenities"/>'+
  '</div>',

  methods: {
    editMethod: function(amenity){
        this.amenity = amenity
    }
  }
});

var app = new Vue({
  el: '#app',
  template:
  '<div>'+
    '<div v-if="!profile">  need to authorize <a href="/login">here</a></div>'+
    '<div v-else>'+
        '<div>{{profile.name}} <a href="/logout">logout</a> </div>'+
        '<amenities-list :amenities="amenities" />'+
    '</div>'+
  '</div>',
  data: {
    amenities: frontendData.amenities,
    profile:frontendData.profile
  },
    created: function(){
//      amenityApi.get().then(result =>
//          result.json().then(data =>
//              data.forEach(amenity => this.amenities.push(amenity))
//          )
//      )
    }
});

