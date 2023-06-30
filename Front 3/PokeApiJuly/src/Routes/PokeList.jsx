import React from 'react'
import { Link, useParams } from 'react-router-dom'
import { usePokeStates } from '../Context'

const PokeList = () => {

    const {pokeState} = usePokeStates()
    const params = useParams()
    console.log(params)
  return (
    <div>
        {pokeState.pokeList.map(poke => <Link to={'/poke/' + poke.name} key={poke.name}>
                <li>{poke.name}</li>
            </Link>)}
    </div>
  )
}

export default PokeList