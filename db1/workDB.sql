SELECT emotions
FROM emotion_state es
JOIN action_type at ON es.action_type_id = at.id
JOIN action a ON a.action_type_id = at.id
JOIN person p ON a.person_id = p.id
WHERE p.person_name = 'Флойд';
