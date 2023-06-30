import axios from 'axios'
import React from 'react'
import { useEffect } from 'react'
import { useParams } from 'react-router-dom'
import { usePokeStates } from '../Context'

const Pokemon = () => {
    const {pokeState, pokeDispatch} = usePokeStates()
    const {name} = useParams()
   

   
    console.log(name)
    const urlPoke = 'https://pokeapi.co/api/v2/pokemon/' + name
    
    useEffect(() => {
        axios(urlPoke)
        .then(res => {
            console.log(res)
            pokeDispatch({type: 'GET_POKE', payload: res.data})
        })
    }, [])
    

  return (
    <div>
        {pokeState.pokemon.name}
        <img src={pokeState.pokemon.sprites?.front_default} alt="" />
        {pokeState.pokemon.abilities?.map(ability => <li>{ability.ability.name}</li>)}
    </div>
  )
}

export default Pokemon